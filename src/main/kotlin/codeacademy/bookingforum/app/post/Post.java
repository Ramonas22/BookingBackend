package codeacademy.bookingforum.app.post;

import codeacademy.bookingforum.app.photo.Photo;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import jakarta.persistence.*;
import org.jetbrains.annotations.Nullable;

import java.util.Date;
import java.util.List;
@Table(name = "post")
@Entity
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


    @ManyToMany(mappedBy = "postPhotos")
    private List<Photo> photoPost;

    @ManyToOne
    @JoinColumn(name = "post_id", insertable = false, updatable = false)
    private UserAuth user;

    public Post() {
    }

    public Post(@Nullable Long postId) {

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public List<Photo> getPhotoPost() {
        return photoPost;
    }

    public void setPhotoPost(List<Photo> photoPost) {
        this.photoPost = photoPost;
    }

    public UserAuth getUser() {
        return user;
    }

    public Post(Long id, String title, String content, Date datePosted, List<Photo> photoPost, UserAuth user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.datePosted = datePosted;
        this.photoPost = photoPost;
        this.user = user;
    }

    public void setUser(UserAuth user) {
        this.user = user;
    }
}
