package codeacademy.bookingforum.app.comment

import org.springframework.stereotype.Service

@Service
class CommentMapper {

    fun fromDtoToEntity(dto: CommentDto?): Comment {
        return Comment(
            id = dto?.id,
            date_commented = dto?.date_commented,
            content = dto?.content,
            post_id = dto?.post_id,
            user_id = dto?.user_id,
            likes = dto?.likes,
            dislikes = dto?.dislikes
        )
    }

    fun fromEntityToDto(entity: Comment?): CommentDto {
        return CommentDto(
            id = entity?.id,
            date_commented = entity?.date_commented,
            content = entity?.content,
            post_id = entity?.post_id,
            user_id = entity?.user_id,
            likes = entity?.likes,
            dislikes = entity?.dislikes,
        )
    }

    fun fromEntityListToDtoList(entityList : List<Comment>?): List<CommentDto>? {
        return if(entityList.isNullOrEmpty()){
            val listOfCommentDto : MutableList<CommentDto> = emptyList<CommentDto>().toMutableList()
            if(entityList?.isEmpty() == false){
                entityList.forEach {
                    listOfCommentDto.add(
                        fromEntityToDto(it)
                    )
                  }
            }
            listOfCommentDto
        }else{
            null
        }
    }
}