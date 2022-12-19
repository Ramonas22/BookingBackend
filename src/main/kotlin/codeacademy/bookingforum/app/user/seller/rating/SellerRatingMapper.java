package codeacademy.bookingforum.app.user.seller.rating;

import codeacademy.bookingforum.app.user.auth.UserAuth;
import codeacademy.bookingforum.app.user.seller.page.SellerPage;
import org.springframework.stereotype.Service;

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

        List<Long> usRating = new ArrayList<>();
        for(UserAuth usrat: entity.getUserRating()){
            usRating.add(usrat.getId());
        }
        dto.setUserRating_id(usRating);

        List<Long> raitingSell = new ArrayList<>();
        for(SellerPage sell: entity.getRatingSeller()){
            raitingSell.add(sell.getId());
        }
        dto.setRaitingSeller_id(raitingSell);
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
        entity.setDate(dto.getDate());

        List<UserAuth> userAuthList = new ArrayList<>();
        for(Long id: dto.getUserRating_id()){
            userAuthList.add(new UserAuth(id));
        }
        entity.setUserRating(userAuthList);

        List<SellerPage> sellerPageList =new ArrayList<>();
        for(Long id: dto.getRaitingSeller_id()){
            sellerPageList.add(new SellerPage(id));
        }
        entity.setRatingSeller(sellerPageList);

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
