package codeacademy.bookingforum.app.topic;

import codeacademy.bookingforum.app.topicCategory.TopicCategory;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import jakarta.persistence.*;

@Table(name = "topic")
@Entity
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



    public Topic() {
    }

    public Topic(Long id) {
        this.id = id;
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

    public Integer getPostCount() {
        return postCount;
    }

    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
    }

    public Integer getReplayCount() {
        return replayCount;
    }

    public void setReplayCount(Integer replayCount) {
        this.replayCount = replayCount;
    }

    public Integer getPostLast() {
        return postLast;
    }

    public void setPostLast(Integer postLast) {
        this.postLast = postLast;
    }

    public Integer getUserLastPost() {
        return userLastPost;
    }

    public void setUserLastPost(Integer userLastPost) {
        this.userLastPost = userLastPost;
    }

    public UserAuth getUser() {
        return user;
    }

    public void setUser(UserAuth user) {
        this.user = user;
    }

    public TopicCategory getTopicCategory() {
        return topicCategory;
    }

    public void setTopicCategory(TopicCategory topicCategory) {
        this.topicCategory = topicCategory;
    }
}
