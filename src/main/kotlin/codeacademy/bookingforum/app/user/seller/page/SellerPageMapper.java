package codeacademy.bookingforum.app.user.seller.page;

import codeacademy.bookingforum.app.purchase.Purchase;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import codeacademy.bookingforum.app.user.seller.rating.SellerRating;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerPageMapper {

    public SellerPageDto toDto(SellerPage entity) {
        if (entity == null) {
            return null;
        } else {
            return new SellerPageDto(
                    entity.getId(),
                    entity.getDescription(),
                    entity.getGalleryLinks(),
                    entity.getPriceMin(),
                    entity.getPriceMax(),
                    entity.getUnavailableDates(),
                    entity.getUserauth().getId(),
                    entity.getSellerRatings().stream().map(sellerRating -> sellerRating.getId()).toList(),
                    entity.getPurchases().stream().map(purchase -> purchase.getId()).toList()
            );
        }
    }

    public SellerPage fromDto(SellerPageDto dto) {
        if (dto == null) {
            return null;
        } else {
            return new SellerPage(
                    dto.getId(),
                    dto.getDescription(),
                    dto.getGalleryLinks(),
                    dto.getPriceMin(),
                    dto.getPriceMax(),
                    dto.getUnavailableDate(),
                    new UserAuth(dto.getUserId()),
                    dto.getSellerRatingsIds().stream().map( id -> new SellerRating(id)).toList(),
                    dto.getPurchasesIds().stream().map( id -> new Purchase(id)).toList()
            );
        }

    }

    public List<SellerPageDto> toDto(List<SellerPage> ent) {
        List<SellerPageDto> dtos = new ArrayList<>();

        for (SellerPage entity : ent) {
            dtos.add((SellerPageDto) toDto(entity));
        }
        return dtos;
    }


}
