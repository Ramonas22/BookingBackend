package codeacademy.bookingforum.app.image;

import codeacademy.bookingforum.app.user.auth.UserAuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ImageMapper {
    @Autowired
    UserAuthRepo userAuthRepo;

    public ImageDto toDto(Image entity){
        if (entity == null){
            return null;
        }
        ImageDto dto = new ImageDto();
        dto.setTags(entity.getTags());
        dto.setDescription(entity.getDescription());
        dto.setType(entity.getType().toString());
        dto.setUsername(entity.getUser().getUsername());
        return dto;

    }

    public Image fromDto(ImageDto dto){
        if (dto == null){
            return null;
        }
        Image entity = new Image();
        entity.setTags(dto.getTags());
        entity.setDescription(dto.getDescription());
        entity.setType(ImageType.valueOf(dto.getType()));
        entity.setUser(userAuthRepo.findByUsername(dto.getUsername().trim()));

        return entity;
    }

    public List<ImageDto> toDto(List<Image> entities) {
        List<ImageDto> dtos = new ArrayList<>();

        for (Image entity : entities) {
            dtos.add(toDto(entity));
        }

        return dtos;
    }
}


