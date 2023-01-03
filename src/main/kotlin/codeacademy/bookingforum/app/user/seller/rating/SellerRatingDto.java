package codeacademy.bookingforum.app.user.seller.rating;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class SellerRatingDto {
    Long id;
    Double rating;
    String comment;
    LocalDateTime date;
    List<Long> userIds;
    List<Long> sellerRatingIds;
}
