package codeacademy.bookingforum.app.post;

import codeacademy.bookingforum.app.image.Image;
import codeacademy.bookingforum.app.topic.Topic;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "post")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "date_posted")
    private LocalDateTime datePosted;
    @OneToOne(cascade = CascadeType.ALL)
    private Image image;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private UserAuth user;
    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    public Post(Long id) {
        this.id = id;
    }
}
