package codeacademy.bookingforum.app.purchase;

import codeacademy.bookingforum.app.configuration.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;

    @Secured({"ROLE_SELLER", "ROLE_ADMIN"})
    @GetMapping("/list/{id}") // ID of SellerPage
    public List<PurchaseDto> getPurchaseList(@PathVariable("id") Long id) {
        return purchaseService.getPurchaseList(id);
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/order")
    public ResponseObject placeOrder(@RequestBody @Valid PurchaseDto purchase, WebRequest request) {
        return purchaseService.placeOrder(purchase, request);
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping("/myorders/{id}") // ID of User
    public List<PurchaseDto> myOrders(@PathVariable("id") Long id) {
        return purchaseService.myOrders(id);
    }

    @Secured({"ROLE_SELLER","ROLE_ADMIN"})
    @DeleteMapping("/delete/{id}") // ID of Purchase
    public ResponseObject delete(@PathVariable("id") Long id, WebRequest request) {
        return purchaseService.delete(id, request);
    }
}
