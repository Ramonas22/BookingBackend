package codeacademy.bookingforum.app.topicCategory

import codeacademy.bookingforum.app.topic.Topic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TopicCategoryService {
    @Autowired
    private lateinit var mapper: TopicCategoryMapper
    @Autowired
    private lateinit var repository: TopicCategoryRepository

    fun getAllTopicCategories(): List<TopicCategoryDto?> {
        return repository.findAll().map { mapper.fromEntityToDto(it) }
    }

    fun getAllTopicCategoryById(id: Long): TopicCategoryDto? {
        return repository.findByIdOrNull(id).let { mapper.fromEntityToDto(it) }
    }

    fun postTopicCategory(topicCategoryDto: TopicCategoryDto?) {
        mapper.fromDtoToEntity(topicCategoryDto)?.let { repository.save(it) }
    }

    fun updateTopicCategory(id: Long, topicCategoryDto: TopicCategoryDto?) {
        TopicCategory(
            id,
            topicCategoryDto?.title,
            topicCategoryDto?.description,
            topicCategoryDto?.role,
            topicCategoryDto?.topics?.map { Topic(it) }
        ).let { repository.save(it) }
    }

    fun deleteTopicCategory(id: Long): String {
        return if(repository.existsById(id)){
            repository.deleteById(id)
            "Deleted user with id $id"
        }else{
            "Id not found"
        }
    }
}