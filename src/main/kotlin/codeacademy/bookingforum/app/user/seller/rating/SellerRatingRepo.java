package codeacademy.bookingforum.app.user.seller.rating;

import codeacademy.bookingforum.app.user.auth.UserAuth;
import codeacademy.bookingforum.app.user.seller.page.SellerPage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SellerRatingRepo extends CrudRepository<SellerRating, Long> {
    List<SellerRating> findByUserAndPage(UserAuth user, SellerPage page);
}
