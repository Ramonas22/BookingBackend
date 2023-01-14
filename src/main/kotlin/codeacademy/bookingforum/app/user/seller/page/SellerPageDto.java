package codeacademy.bookingforum.app.user.seller.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerPageDto {
    Long id;
    String description;
    List<Long> galleryImageIds;
    Double priceMin;
    Double priceMax;
    List<Date> unavailableDates;
    Long userId;
    List<Long> sellerRatingIds;
}
