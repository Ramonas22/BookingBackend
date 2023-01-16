package codeacademy.bookingforum.app.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    Long id;
    LocalDateTime dateCommented;
    String content;
    Integer likes;
    Integer dislikes;
    Long userId;
    Long postId;
}
