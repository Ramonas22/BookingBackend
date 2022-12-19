package codeacademy.bookingforum.app.photo;

import codeacademy.bookingforum.app.user.auth.UserAuth;

import java.util.ArrayList;
import java.util.List;

public class PhotoMapper {

    public PhotoDto toDto(Photo entity){
        if (entity == null){
            return null;
        }
        PhotoDto dto = new PhotoDto();
        dto.setId(entity.getId());
        dto.setImageUrl(entity.getImageUrl());
        dto.setTags(entity.getTags());
        dto.setDescription(entity.getDescription());
        dto.setType(entity.getType().toString());

        List<Long> orderUserList = new ArrayList<>();
        for(UserAuth user: entity.getUserPhotos()){
            orderUserList.add(user.getId());
        }
        dto.setUserPhotos_id(orderUserList);

//        List<Long> postPhotoList = new ArrayList<>();
//        for(Post user: entity.getUserPhotos()){
//            orderUserList.add(user.getId());
//        }

        return dto;

    }
}
