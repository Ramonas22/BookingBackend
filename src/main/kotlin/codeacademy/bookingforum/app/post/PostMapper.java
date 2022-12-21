package codeacademy.bookingforum.app.post;

import codeacademy.bookingforum.app.photo.Photo;
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
                entity.getPhotoPost().stream().map(photo -> photo.getId()).toList(),
                entity.getUser().getId()
                );

    }

    public Post fromDto(PostDto dto) {
        if (dto == null) {
            return null;
        }

        UserAuth user = new UserAuth();
        user.setId(dto.getUser_id());
        return new Post(
                dto.getId(),
                dto.getTitle(),
                dto.getContent(),
                dto.getDatePosted(),
                dto.getPhotoPost_id().stream().map(Photo::new).toList(),
                user
        );
    }

    public List<PostDto> toDtos(List<Post> list) {
        if (list == null) {
            return null;
        } else if (list.size() == 0) {
            return new ArrayList<>();
        } else {
            List<PostDto> posts = new ArrayList<>();
            for (Post post : list) {
                posts.add(toDto(post));
            }
            return posts;
        }
    }


}
