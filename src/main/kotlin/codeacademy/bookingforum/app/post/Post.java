package codeacademy.bookingforum.app.post;

import codeacademy.bookingforum.app.image.Image;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import java.util.Date;
import java.util.List;
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
    private Date datePosted;


    @OneToMany(mappedBy = "post")
    private List<Image> images;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private UserAuth user;

    public Post(@Nullable Long postId) {

    }
}
