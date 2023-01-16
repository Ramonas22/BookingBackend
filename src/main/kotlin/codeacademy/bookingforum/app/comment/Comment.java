package codeacademy.bookingforum.app.comment;

import codeacademy.bookingforum.app.post.Post;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "date_commented")
    LocalDateTime dateCommented;
    @Column(name = "content")
    String content;
    @Column(name = "likes")
    Integer likes;
    @Column(name = "dislikes")
    Integer dislikes;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    UserAuth user;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "post_id")
    Post post;

    public Comment(Long id) {
        this.id = id;
    }
}
