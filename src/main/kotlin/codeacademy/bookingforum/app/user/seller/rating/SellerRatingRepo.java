package codeacademy.bookingforum.app.user.seller.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SellerRatingRepo extends JpaRepository<SellerRating, Long> {
    @Query(value = "select * from seller_rating where user_id = :userId AND page_id = :pageId LIMIT 1", nativeQuery = true)
    SellerRating findByUserIdAndPageId(@Param("userId")Long userId, @Param("pageId")Long pageId);

}
