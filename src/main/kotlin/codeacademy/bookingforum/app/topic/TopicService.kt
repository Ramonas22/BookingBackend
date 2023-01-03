package codeacademy.bookingforum.app.topic

import codeacademy.bookingforum.app.topicCategory.TopicCategory
import codeacademy.bookingforum.app.user.auth.UserAuth
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TopicService {

    @Autowired
    private lateinit var mapper: TopicMapper

    @Autowired
    private lateinit var repository: TopicRepository
    fun getAllTopics(): List<TopicDto?> {
        return repository.findAll().map { mapper.toDto(it) }
    }

    fun getTopicById(id: Long): TopicDto? {
        return repository.findByIdOrNull(id).let { mapper.toDto(it) }
    }

    fun postTopic(topicDto: TopicDto?) {
        mapper.fromDto(topicDto)?.let { repository.save(it) }
    }

    fun updateTopic(id: Long, topicDto: TopicDto?): String {
        return if (repository.existsById(id)) {
            repository.save(
                Topic(
                    id,
                    topicDto?.title,
                    topicDto?.description,
                    topicDto?.postCount,
                    topicDto?.replayCount,
                    topicDto?.postLast,
                    topicDto?.userLastPost,
                    UserAuth(topicDto?.userId),
                    TopicCategory(topicDto?.topicCategory_id)
                )
            )
            "Topic with Id $id updated"
        } else {
            "Topic with $id does not exist"
        }
    }

    fun deleteTopic(id: Long): String {
        return if (repository.existsById(id)) {
            repository.deleteById(id)
            "Deleted topic with id $id"
        } else {
            "Topic with $id not found"
        }
    }
}