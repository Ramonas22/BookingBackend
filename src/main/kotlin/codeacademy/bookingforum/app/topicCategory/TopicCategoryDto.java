package codeacademy.bookingforum.app.topicCategory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class TopicCategoryDto {
    private Long id;
    private String title;
    private String description;
    private List<String> roles;
}
