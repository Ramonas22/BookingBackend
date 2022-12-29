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

        List<Long> orderUserList = new ArrayList<>();
        for(UserAuth user: entity.getUsers()){
            orderUserList.add(user.getId());
        }
        dto.setOrderUser_id(orderUserList);

        List<Long> orderSellerList = new ArrayList<>();
        for(SellerPage seller: entity.getSellers()){
            orderUserList.add(seller.getId());
        }
        dto.setOrderSeller_id(orderSellerList);

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

        List<UserAuth> userAuthList = new ArrayList<>();
        for(Long id: dto.getOrderUser_id()){
            UserAuth user = new UserAuth();
            user.setId(id);
        }
        entity.setUsers(userAuthList);

        List<SellerPage> sellerPageList = new ArrayList<>();
        for(Long id: dto.getOrderSeller_id()){
            sellerPageList.add(new SellerPage(id));
        }
        entity.setSellers(sellerPageList);

        return entity;
    }

    public List<PurchaseDto> toDtoList(List<Purchase> entities) {
        List<PurchaseDto> dtos = new ArrayList<>();
        for (Purchase entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }
//    public List<Purchase> fromDtoList(List<PurchaseDto> dtos) {
//        List<Purchase> entities = new ArrayList<>();
//        dtos.forEach(dto -> {entities.add(this.fromDto(dto));});
//        return entities;
//    }
}
