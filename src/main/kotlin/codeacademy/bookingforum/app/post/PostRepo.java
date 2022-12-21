package codeacademy.bookingforum.app.post;

import org.springframework.data.repository.CrudRepository;

public interface PostRepo extends CrudRepository <Post, Long> {
}
