package codeacademy.bookingforum.app.report;

import codeacademy.bookingforum.app.comment.Comment;
import codeacademy.bookingforum.app.post.Post;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Report")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "reason")
    String reason;
    @Column(name = "description")
    String description;
    @Column(name = "report_date")
    LocalDateTime reportDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    UserAuth user;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    Post post;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id")
    Comment comment;
}
