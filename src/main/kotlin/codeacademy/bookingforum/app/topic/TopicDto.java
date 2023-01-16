package codeacademy.bookingforum.app.topic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TopicDto {
    private Long id;
    private String title;
    private String description;
    private Integer postCount;
    private Integer replyCount;
    private Long lastPostId;
    private Long userId;
    private Long sectionId;
}
