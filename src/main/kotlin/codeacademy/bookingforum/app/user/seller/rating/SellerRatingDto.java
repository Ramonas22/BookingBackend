package codeacademy.bookingforum.app.user.seller.rating;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class SellerRatingDto {
    private Long id;
    private Double rating;
    private String comment;
    private LocalDateTime date;
    private List<Long> userIds;
    private List<Long> sellerRatingIds;
}
