package codeacademy.bookingforum.app.user.seller.page;

import codeacademy.bookingforum.app.user.auth.UserAuth;
import codeacademy.bookingforum.app.user.seller.rating.SellerRating;
import org.springframework.stereotype.Service;

@Service
public class SellerPageMapper {

    public SellerPageDto toDtoList(SellerPage entity) {
        if (entity == null) {
            return null;
        } else {
            return new SellerPageDto(
                    entity.getId(),
                    entity.getDescription(),
                    entity.getGalleryImageIds(),
                    entity.getPriceMin(),
                    entity.getPriceMax(),
                    entity.getUnavailableDates(),
                    entity.getUser().getId(),
                    entity.getRatings().stream().map(SellerRating::getId).toList()
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
                    dto.getGalleryImageIds(),
                    dto.getPriceMin(),
                    dto.getPriceMax(),
                    dto.getUnavailableDates(),
                    new UserAuth(dto.getUserId()),
                    dto.getSellerRatingIds().stream().map(SellerRating::new).toList(),
                    null
                    );
        }
    }

    public SellerPage update(SellerPage original, SellerPageDto updated) {
        original.setPriceMin(updated.getPriceMin());
        original.setPriceMax(updated.getPriceMax());
        original.setDescription(updated.getDescription());

        return original;
    }
}