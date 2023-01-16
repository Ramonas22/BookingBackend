package codeacademy.bookingforum.app.topicCategory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicCategoryRepo extends CrudRepository<TopicCategory, Long> {
}
