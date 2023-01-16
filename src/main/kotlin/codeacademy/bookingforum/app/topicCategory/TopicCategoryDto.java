package codeacademy.bookingforum.app.topicCategory;

import codeacademy.bookingforum.app.topic.Topic;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter

public class TopicCategoryDto {
    private Long id;
    @NotEmpty(message = "No title provided.")
    private String title;
    @NotEmpty(message = "No description provided.")
    private String description;
    private List<String> roles;
    private List<Topic> topics;
}
