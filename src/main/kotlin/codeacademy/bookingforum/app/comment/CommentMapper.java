package codeacademy.bookingforum.app.comment;

import codeacademy.bookingforum.app.post.Post;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentMapper {
    public CommentDto toDto(Comment entity) {
        if (entity == null) {
            return null;
        }
        CommentDto dto = new CommentDto();

        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setDateCommented(entity.getDateCommented());
        dto.setLikes(entity.getLikes());
        dto.setDislikes(entity.getDislikes());
        dto.setUserId(entity.getUser().getId());
        dto.setPostId(entity.getPost().getId());

        return dto;
    }

    public Comment fromDto(CommentDto dto) {
        if (dto == null) {
            return null;
        }
        Comment entity = new Comment();

        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        if (dto.getDateCommented() == null) {
            entity.setDateCommented(LocalDateTime.now());
        } else {
            entity.setDateCommented(dto.getDateCommented());
        }
        entity.setLikes(dto.getLikes());
        entity.setDislikes(dto.getDislikes());
        entity.setUser(new UserAuth(dto.getUserId()));
        entity.setPost(new Post(dto.getPostId()));

        return entity;
    }
}
