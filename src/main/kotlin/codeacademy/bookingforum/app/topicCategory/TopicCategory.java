package codeacademy.bookingforum.app.topicCategory;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "topic_category")
@NoArgsConstructor
@Getter
@Setter
public class TopicCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "roles")
    private List<String> roles;

    public TopicCategory(Long id) {
        this.id = id;
    }

    public TopicCategory(Long id, String title, String description, List<String> roles) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.roles = roles;
    }
}
