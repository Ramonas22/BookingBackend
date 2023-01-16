package codeacademy.bookingforum.app.topic;

import codeacademy.bookingforum.app.post.Post;
import codeacademy.bookingforum.app.topicCategory.TopicCategory;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "topic")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title", unique = true)
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "post_count")
    private Integer postCount;
    @Column(name = "reply_count")
    private Integer replyCount;
    @Column(name = "last_post_id")
    private Long lastPostId;
    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private UserAuth user;
    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private TopicCategory section;
    @OneToMany(mappedBy = "topic")
    private List<Post> posts;

    public Topic(Long id) {
        this.id = id;
    }
}
