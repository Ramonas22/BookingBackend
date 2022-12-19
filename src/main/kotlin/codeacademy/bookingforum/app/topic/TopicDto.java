package codeacademy.bookingforum.app.topic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicDto {

    private Long id;

    private String title;

    private String description;

    private Integer postCount;

    private Integer replayCount;

    private Integer postLast;

    private Integer userLastPost;

    private Long user_id;

    private Long topicCategory_id;
}
