package codeacademy.bookingforum.app.photo;

import codeacademy.bookingforum.app.post.Post;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import jakarta.persistence.*;

import java.util.List;

@Table(name = "photo")
@Entity
public class Photo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    private List<String> tags;

    private String description;

    private PhotoType type;


    @ManyToMany
    @JoinTable(
            name = "user_photo",
            joinColumns = @JoinColumn(name = "photo_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserAuth> userPhotos;

    @ManyToMany
    @JoinTable(
            name = "post_photo",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<Post> postPhotos;

    public Photo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PhotoType getType() {
        return type;
    }

    public void setType(PhotoType type) {
        this.type = type;
    }

    public List<UserAuth> getUserPhotos() {
        return userPhotos;
    }

    public void setUserPhotos(List<UserAuth> userPhotos) {
        this.userPhotos = userPhotos;
    }

    public List<Post> getPostPhotos() {
        return postPhotos;
    }

    public void setPostPhotos(List<Post> postPhotos) {
        this.postPhotos = postPhotos;
    }
}
