package codeacademy.bookingforum.app.post;

import lombok.*;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime datePosted;
    private Long imageId;
    private Long userId;
    private Long topicId;
}
