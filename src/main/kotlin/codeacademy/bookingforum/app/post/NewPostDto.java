package codeacademy.bookingforum.app.post;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewPostDto {
    @NotEmpty(message = "Please come up with a title.")
    private String title;
    @NotEmpty(message = "You can't create a post with no content.")
    private String content;
    @NotEmpty(message = "No post owner provided!")
    private Long userId;
    @NotEmpty(message = "No topic provided!")
    private Long topicId;
}
