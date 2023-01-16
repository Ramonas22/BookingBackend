package codeacademy.bookingforum.app.post;

import codeacademy.bookingforum.app.image.Image;
import codeacademy.bookingforum.app.topic.Topic;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostMapper {

    public PostDto toDto(Post entity) {
        if (entity == null) {
            return null;
        }

        return new PostDto(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getDatePosted(),
                entity.getImage().getId(),
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
