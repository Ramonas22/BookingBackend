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

        List<Long> userIds = new ArrayList<>();
        for(UserAuth usrat: entity.getUsers()){
            userIds.add(usrat.getId());
        }
        dto.setUserIds(userIds);

        List<Long> sellerPageIds = new ArrayList<>();
        for(SellerPage sell: entity.getSellerPages()){
            sellerPageIds.add(sell.getId());
        }
        dto.setSellerRatingIds(sellerPageIds);
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

        List<UserAuth> userIds = new ArrayList<>();
        for(Long id: dto.getUserIds()){
            userIds.add(new UserAuth(id));
        }
        entity.setUsers(userIds);

        List<SellerPage> sellerPageList =new ArrayList<>();
        for(Long id: dto.getSellerRatingIds()){
            SellerPage page = new SellerPage();
            page.setId(id);
            sellerPageList.add(page);
        }
        entity.setSellerPages(sellerPageList);

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
