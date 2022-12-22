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

    private Long id;

    private String description;

    private List<String> galaleryLinks;

    private Double priceMin;

    private Double priceMax;

    private List<Date> unavailableDate;




}
