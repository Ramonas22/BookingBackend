package codeacademy.bookingforum.app.post;

import codeacademy.bookingforum.app.image.Image;
import codeacademy.bookingforum.app.topic.Topic;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostMapper {

    public PostDto toDto(Post entity) {
        if (entity == null) {
            return null;
        }
        Long imageId = null;
        if (entity.getImage() != null) {
            imageId = entity.getImage().getId();
        }

        return new PostDto(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getDatePosted(),
                imageId,
                entity.getUser().getId(),
                entity.getTopic().getId()
                );

    }

    public Post fromDto(PostDto dto) {
        if (dto == null) {
            return null;
        }

        return new Post(
                dto.getId(),
                dto.getTitle(),
                dto.getContent(),
                dto.getDatePosted(),
                new Image(dto.getImageId()),
                new UserAuth(dto.getUserId()),
                new Topic(dto.getTopicId()),
                new ArrayList<>()
        );
    }
    public Post fromNewDto(NewPostDto dto) {
        if (dto == null) {
            return null;
        }

        return new Post(
                dto.getTitle(),
                dto.getContent(),
                LocalDateTime.now(),
                new UserAuth(dto.getUserId()),
                new Topic(dto.getTopicId())
        );
    }

    public List<PostDto> toDtoList(List<Post> entities) {
        if (entities == null) {
            return null;
        }

        List<PostDto> dtoList = new ArrayList<>();
        entities.forEach(entity -> dtoList.add(toDto(entity)));

        return dtoList;
    }
}
