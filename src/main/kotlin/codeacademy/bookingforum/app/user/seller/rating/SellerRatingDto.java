package codeacademy.bookingforum.app.user.seller.rating;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerRatingDto {

    private Long id;

    private Integer rating;

    private String comment;

    private Date date;

    private List<Long> userRating_id;

    private List<Long> raitingSeller_id;

}
