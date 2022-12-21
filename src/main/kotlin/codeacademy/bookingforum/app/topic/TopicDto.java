package codeacademy.bookingforum.app.topic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TopicDto {

    private Long id;
    private String title;
    private String description;
    private Integer postCount;
    private Integer replayCount;
    private Integer postLast;
    private Integer userLastPost;
    private Long userId;
    private Long topicCategory_id;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTopicCategory_id() {
        return topicCategory_id;
    }

    public void setTopicCategory_id(Long topicCategory_id) {
        this.topicCategory_id = topicCategory_id;
    }
}
