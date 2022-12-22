package codeacademy.bookingforum.app.photo;

import codeacademy.bookingforum.app.post.Post;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
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

        List<Long> postList = new ArrayList<>();
        for (Post posts: entity.getPostPhotos()){
            postList.add(posts.getId());
        }
        dto.setPostPhoto_id(postList);

//        List<Long> postPhotoList = new ArrayList<>();
//        for(Post user: entity.getUserPhotos()){
//            orderUserList.add(user.getId());
//        }

        return dto;

    }

    public Photo fromDto(PhotoDto dto){
        if (dto == null){
            return null;
        }
        Photo entity = new Photo();
        entity.setId(dto.getId());
        entity.setImageUrl(dto.getImageUrl());
        entity.setTags(dto.getTags());
        entity.setDescription(dto.getDescription());
        entity.setType(dto.getType().toString());
        List<Photo> photos = new ArrayList<>();
        for(Long id: dto.getPostPhoto_id()){
            photos.add(new Photo(id));
        }
        entity.setPostPhotos(photos);
        List<UserAuth> users =new ArrayList<>();
        for(Long id: dto.getUserPhotos_id()){
            UserAuth user =new UserAuth();
            user.setId(id);
        }
        entity.setUserPhotos(users);

        return entity;
    }

    public List<PhotoDto> toDto(List<Photo> entities) {
        List<PhotoDto> dtos = new ArrayList<>();

        for (Photo entity : entities) {
            dtos.add(toDto(entity));
        }

        return dtos;
    }
}


