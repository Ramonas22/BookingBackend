package codeacademy.bookingforum.app.image;

import codeacademy.bookingforum.app.post.Post;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Table(name = "image")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Image {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(columnDefinition = "varchar(255)")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "data")
    @Lob
    private byte[] data;
    @Column(name = "extension")
    private String extension;
    @Column(name = "tags")
    private List<String> tags;
    @Column(name = "description")
    private String description;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ImageType type;
    @ManyToOne
    @JoinColumn(name = "user_image", nullable = false)
    private UserAuth user;
    @ManyToOne
    @JoinColumn(name = "post_image")
    private Post post;

    public Image(String id) {
        this.id = id;
    }

    public Image(String name, byte[] data, List<String> tags, String description, ImageType type, UserAuth user, Post post, String extension) {
        this.name = name;
        this.data = data;
        this.tags = tags;
        this.description = description;
        this.type = type;
        this.user = user;
        this.post = post;
        this.extension = extension;
    }
}
