package codeacademy.bookingforum.app.post;

import codeacademy.bookingforum.app.topic.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {
    List<Post> findAllByTopic(Topic topic);
}
