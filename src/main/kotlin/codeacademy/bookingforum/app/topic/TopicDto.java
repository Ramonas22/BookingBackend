package codeacademy.bookingforum.app.topic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TopicDto {

    private Long id;
    private String title;
    private String description;
    private Integer postCount;
    private Integer replayCount;
    private Integer postLast;
    private Integer userLastPost;
    private Long userId;
    private Long topicCategory_id;
}
