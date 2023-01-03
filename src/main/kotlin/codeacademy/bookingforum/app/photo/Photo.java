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

    @Column(name = "image_Url")
    private String imageUrl;

    @Column(name = "tags")
    private List<String> tags;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
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

    public Photo(Long id, String imageUrl, List<String> tags, String description, PhotoType type, List<UserAuth> userPhotos, List<Post> postPhotos) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.tags = tags;
        this.description = description;
        this.type = type;
        this.userPhotos = userPhotos;
        this.postPhotos = postPhotos;
    }

    public Photo(Long id) {
        this.id = id;
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

    public void setType(String type) {
        this.type = PhotoType.valueOf(type);
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

    public void setPostPhotos(List<Photo> photos) {
        this.postPhotos = postPhotos;
    }
}
