package codeacademy.bookingforum.app.user.seller.rating;

import codeacademy.bookingforum.app.user.auth.UserAuth;
import codeacademy.bookingforum.app.user.seller.page.SellerPage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SellerRatingMapper {

    public SellerRatingDto toDto(SellerRating entity){
        if(entity == null){
            return null;
        }
        SellerRatingDto dto = new SellerRatingDto();
        dto.setId(entity.getId());
        dto.setRating(entity.getRating());
        dto.setComment(entity.getComment());
        dto.setDate(entity.getDate());
        dto.setUserId(entity.getUser().getId());
        dto.setSellerPageId(entity.getPage().getId());
        return dto;
    }


    public SellerRating fromDto(SellerRatingDto dto){
        if(dto == null){
            return null;
        }
        SellerRating entity = new SellerRating();
        entity.setId(dto.getId());
        entity.setRating(dto.getRating());
        entity.setComment(dto.getComment());
        entity.setDate(LocalDateTime.now());
        entity.setUser(new UserAuth(dto.getUserId()));
        entity.setPage(new SellerPage(dto.getSellerPageId()));

        return entity;
    }

    public List<SellerRatingDto> toDto(List<SellerRating> entities) {
        List<SellerRatingDto> dtos = new ArrayList<>();

        for (SellerRating entity : entities) {
            dtos.add(toDto(entity));
        }

        return dtos;
    }


}
