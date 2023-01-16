package codeacademy.bookingforum.app.topic;

import codeacademy.bookingforum.app.topicCategory.TopicCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findAllBySection(TopicCategory section);
    Topic findByTitle(String title);
}
