package codeacademy.bookingforum.app.topicCategory

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TopicCategoryRepository: CrudRepository<TopicCategory, Long> {
}