package codeacademy.bookingforum.app.purchase;

import codeacademy.bookingforum.app.user.auth.UserAuth;
import codeacademy.bookingforum.app.user.seller.page.SellerPage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseMapper {

    public PurchaseDto toDto(Purchase entity){
        if(entity == null){
            return null;
        }
        PurchaseDto dto = new PurchaseDto();
        dto.setId(entity.getId());
        dto.setDetails(entity.getDetails());
        dto.setBookedDate(entity.getBookedDate());
        dto.setUserId(entity.getUser().getId());
        dto.setPageId(entity.getSellerPage().getId());

        return dto;
    }

    public Purchase fromDto(PurchaseDto dto){
        if (dto == null){
            return null;
        }
       Purchase entity = new Purchase();
        entity.setId(dto.getId());
        entity.setDetails(dto.getDetails());
        entity.setBookedDate(dto.getBookedDate());
        entity.setUser(new UserAuth(dto.getUserId()));
        entity.setSellerPage(new SellerPage(dto.getPageId()));

        return entity;
    }

    public List<PurchaseDto> toDtoList(List<Purchase> entities) {
        List<PurchaseDto> dtos = new ArrayList<>();
        for (Purchase entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }
}
