package codeacademy.bookingforum.app.purchase;

import codeacademy.bookingforum.app.configuration.ResponseObject;
import codeacademy.bookingforum.app.ecxeption.global.UnsatisfiedExpectationException;
import codeacademy.bookingforum.app.ecxeption.sellerPage.PageNotFoundException;
import codeacademy.bookingforum.app.ecxeption.sellerPage.PurchaseNotFoundException;
import codeacademy.bookingforum.app.ecxeption.user.UserNotFoundException;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import codeacademy.bookingforum.app.user.auth.UserAuthRepo;
import codeacademy.bookingforum.app.user.seller.page.SellerPage;
import codeacademy.bookingforum.app.user.seller.page.SellerPageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

@Service
public class PurchaseService {
    @Autowired
    PurchaseRepo purchaseRepo;
    @Autowired
    PurchaseMapper purchaseMapper;
    @Autowired
    SellerPageRepo pageRepo;
    @Autowired
    UserAuthRepo userRepo;
    @Autowired
    SellerPageRepo sellerPageRepo;

    // Get a list of all previous purchases of provided page
    public List<PurchaseDto> getPurchaseList(Long id) {
        SellerPage page = pageRepo.findById(id).orElse(null);
        if (page == null) {
            throw new PageNotFoundException("Page with id "+id+" does not exist!");
        }
        checkAuth(page.getUser(), "You can't view other seller's orders!");

        List<Purchase> purchaseList = purchaseRepo.findAllBySellerPage(page);
        if (purchaseList == null) {
            throw new PurchaseNotFoundException("You have no orders.");
        }

        return purchaseMapper.toDtoList(purchaseList);
    }

    // Place a new order
    public ResponseObject placeOrder(PurchaseDto purchase, WebRequest request) {
        UserAuth user = userRepo.findById(purchase.getUserId()).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User with id "+purchase.getUserId()+" does not exist!");
        }
        checkAuth(user, "You can't place an order for someone else!");

        SellerPage page = sellerPageRepo.findById(purchase.getPageId()).orElse(null);
        if (page == null) {
            throw new PageNotFoundException("Page with id "+purchase.getPageId()+" does not exist!");
        }
        if (page.getUnavailableDates() == null) {
            page.setUnavailableDates(new ArrayList<>());
        }
        Purchase oldOrder = purchaseRepo.findBySellerPageAndUser(page, user);
        if (oldOrder != null) {
            throw new UnsatisfiedExpectationException("You already ordered from this seller!");
        }
        page.getUnavailableDates().add(new Date());
        pageRepo.save(page);

        purchaseRepo.save(purchaseMapper.fromDto(purchase));
        return new ResponseObject(Collections.singletonList("Successfully placed order!"), HttpStatus.CREATED, request);
    }

    // Get all orders of current user.
    public List<PurchaseDto> myOrders(Long id) {
        UserAuth user = userRepo.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User with id "+id+" does not exist!");
        }
        checkAuth(user, "You can't view other users orders!");
        List<Purchase> myOrders = purchaseRepo.findAllByUser(user);
        if (myOrders == null) {
            throw new PurchaseNotFoundException("You have not placed any orders yet!");
        }

        return purchaseMapper.toDtoList(myOrders);
    }

    // Delete (refuse to take) an order
    public ResponseObject delete(Long id, WebRequest request) {
        Purchase order = purchaseRepo.findById(id).orElse(null);
        if (order == null) {
            throw new PurchaseNotFoundException("Order with id "+id+" does not exist!");
        }

        UserAuth user = userRepo.findById(order.getSellerPage().getUser().getId()).orElse(null);
        if (user == null) {
            throw new UnsatisfiedExpectationException("Non-existing seller on order!");
        }
        checkAuth(user,"You can't delete another seller's order!");

        purchaseRepo.delete(order);

        return new ResponseObject(Collections.singletonList("Order deleted successfully!"), HttpStatus.OK, request);
    }

    // Validate authorization, check whether user is requesting his own resource
    void checkAuth(UserAuth user, String message) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> roles = new ArrayList<>();
        user.getRoles().forEach(role -> roles.add(role.getDisplayName()));

        if (roles.contains("ROLE_ADMIN")) {
            return;
        }
        if (!userDetails.getUsername().equals(user.getUsername())) {
            throw new UnsatisfiedExpectationException(message);
        }
    }

}
