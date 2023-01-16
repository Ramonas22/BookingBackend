package codeacademy.bookingforum.app.topicCategory;

import codeacademy.bookingforum.app.topic.Topic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "topic_category")
@NoArgsConstructor
@AllArgsConstructor
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
    @OneToMany(mappedBy = "section")
    private List<Topic> topics;

    public TopicCategory(Long id) {
        this.id = id;
    }

}
