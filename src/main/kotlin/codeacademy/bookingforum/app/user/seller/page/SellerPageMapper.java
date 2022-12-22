package codeacademy.bookingforum.app.user.seller.page;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerPageMapper {

    public SellerPageDto toDto(SellerPage entity){
        if(entity == null){
            return null;
        }
        SellerPageDto dto = new SellerPageDto();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setPriceMin(entity.getPriceMin());
        dto.setPriceMax(entity.getPriceMax());

//        List<String> galleryLinks = new ArrayList<>();
//        for(SellerPage link: entity.getGalleryLinks()){
//            galleryLinks.add(link.getGalleryLinks());
//        }
//        dto.setGalaleryLinks(galleryLinks);
        dto.setGalaleryLinks(entity.getGalleryLinks());
        dto.setUnavailableDate(entity.getUnavailableDates());
        return dto;
    }

    public SellerPage fromDto(SellerPageDto dto){
        if(dto == null) {
            return null;
        }
        SellerPage entity = new SellerPage();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setPriceMin(dto.getPriceMin());
        entity.setPriceMax(dto.getPriceMax());
        entity.setGalleryLinks(dto.getGalaleryLinks());
        entity.setUnavailableDates(dto.getUnavailableDate());

        return entity;

    }

    public List<SellerPageDto> toDto(List<SellerPage> ent){
        List<SellerPageDto> dtos = new ArrayList<>();

        for(SellerPage entity : ent){
            dtos.add((SellerPageDto) toDto(entity));
        }
        return dtos;
    }



}
