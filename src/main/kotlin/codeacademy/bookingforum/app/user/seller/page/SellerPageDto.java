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

    List<String> galaleryLinks;

    Double priceMin;

    Double priceMax;

    List<Date> unavailableDate;

    Long userId;

    List<Long> sellerRatingsIds;

    List<Long> purchasesIds;


}
