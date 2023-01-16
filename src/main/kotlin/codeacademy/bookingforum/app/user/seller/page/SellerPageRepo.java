package codeacademy.bookingforum.app.user.seller.page;

import codeacademy.bookingforum.app.user.auth.UserAuth;
import org.springframework.data.repository.CrudRepository;

public interface SellerPageRepo extends CrudRepository<SellerPage, Long> {
    SellerPage findByUser(UserAuth user);
}
