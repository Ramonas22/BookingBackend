package codeacademy.bookingforum.app.topic;

import codeacademy.bookingforum.app.topicCategory.TopicCategory;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "topic")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Integer postCount;

    private Integer replayCount;

    private Integer postLast;

    private Integer userLastPost;


    @ManyToOne
    @JoinColumn(name = "topic_id", insertable = false, updatable = false)
    private UserAuth user;

    @ManyToOne
    @JoinColumn(name = "topic_category_id", insertable = false, updatable = false)
    private TopicCategory topicCategory;

    public Topic(Long id, String title, String description, Integer postCount, Integer replayCount, Integer postLast, Integer userLastPost, UserAuth user, TopicCategory topicCategory) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.postCount = postCount;
        this.replayCount = replayCount;
        this.postLast = postLast;
        this.userLastPost = userLastPost;
        this.user = user;
        this.topicCategory = topicCategory;
    }
}
