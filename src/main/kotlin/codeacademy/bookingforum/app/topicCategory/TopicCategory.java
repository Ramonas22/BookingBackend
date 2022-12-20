package codeacademy.bookingforum.app.topicCategory;

import codeacademy.bookingforum.app.topic.Topic;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "topic_category")
public class TopicCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private List<String> role;


    @OneToMany
    @JoinColumn(name = "topic_category_id")
    private List<Topic> topics;

    public TopicCategory(Long topicCategoryId) {
    }

    public TopicCategory(Long id, String title, String description, List<String> role, List<Topic> topics) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.role = role;
        this.topics = topics;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
}
