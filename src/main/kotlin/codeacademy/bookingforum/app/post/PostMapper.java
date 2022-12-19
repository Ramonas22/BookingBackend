package codeacademy.bookingforum.app.post;

import codeacademy.bookingforum.app.photo.Photo;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostMapper {

    public PostDto toDto(Post entity){
        if(entity == null){
            return null;
        }
        PostDto dto = new PostDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setDatePosted(entity.getDatePosted());

        List<Long> post = new ArrayList<>();
        for(Photo photo: entity.getPhotoPost()){
            post.add(photo.getId());
        }
        dto.setPhotoPost_id(post);
        dto.setUser_id(entity.getId());

        return dto;
    }

    public Post fromDto(PostDto dto){
        if(dto == null){
            return null;
        }
        List<Photo> photos = new ArrayList<>();
        for(Long photo_id: dto.getPhotoPost_id()){
            photos.add(new Photo(photo_id));
        }
        return new Post(
                dto.getId(),
                dto.getTitle(),
                dto.getContent(),
                dto.getDatePosted(),
                photos,
                new UserAuth(dto.getUser_id())
        );
    }

    public List<PostDto> toDtos(List<Post> list){
        if( list == null){
            return null;
        }else if (list.size() == 0){
            return new ArrayList<>();
        }else{
            List<PostDto> posts = new ArrayList<>();
            for(Post post: list){
                posts.add(toDto(post));
            }
            return posts;
        }
    }


}
