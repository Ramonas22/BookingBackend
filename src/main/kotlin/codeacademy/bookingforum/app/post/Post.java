package codeacademy.bookingforum.app.post;

import codeacademy.bookingforum.app.photo.Photo;
import jakarta.persistence.ManyToMany;

import java.util.List;

public class Post {

    @ManyToMany(mappedBy = "postPhotos")
    private List<Photo> photoPost;
}
