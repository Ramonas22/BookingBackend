package codeacademy.bookingforum.app.post;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDto {
    private Long id;
    @NotEmpty(message = "Please come up with a title.")
    private String title;
    @NotEmpty(message = "You can't create a post with no content.")
    private String content;
    private LocalDateTime datePosted;
    private Long imageId;
    @NotEmpty(message = "No post owner provided!")
    private Long userId;
    @NotEmpty(message = "No topic provided!")
    private Long topicId;
}
