package codeacademy.bookingforum.app.purchase;

import codeacademy.bookingforum.app.user.auth.UserAuth;
import codeacademy.bookingforum.app.user.seller.page.SellerPage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PurchaseRepo extends CrudRepository<Purchase, Long> {
    List<Purchase> findAllBySellerPage(SellerPage page);
    List<Purchase> findAllByUser(UserAuth user);
}
