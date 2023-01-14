package codeacademy.bookingforum.app.user.seller.rating;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class SellerRatingDto {
    Long id;
    short rating;
    String comment;
    LocalDateTime date;
    Long userId;
    Long sellerPageId;
}
