package codeacademy.bookingforum.app.topicCategory

import codeacademy.bookingforum.app.topic.Topic
import org.springframework.stereotype.Service

@Service
class TopicCategoryMapper {

    fun fromDtoToEntity(dto: TopicCategoryDto?): TopicCategory? {
        return TopicCategory(
            dto?.id,
            dto?.title,
            dto?.description,
            dto?.role,
            dto?.topics?.map { Topic(it) }
        )
    }

    fun fromEntityToDto(entity: TopicCategory?): TopicCategoryDto? {
        return TopicCategoryDto(
            id = entity?.id,
            title = entity?.title,
            description = entity?.description,
            role = entity?.role,
            topics = entity?.topics?.map { it.id }
        )
    }

    fun fromEntityListToDtoList(entityList: List<TopicCategory?>): List<TopicCategoryDto?> {
        return entityList.map { fromEntityToDto(it) }
    }
}