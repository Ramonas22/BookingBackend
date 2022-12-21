package codeacademy.bookingforum.app.comment

import codeacademy.bookingforum.app.post.Post
import codeacademy.bookingforum.app.user.auth.UserAuth
import org.springframework.stereotype.Service

@Service
class CommentMapper {

    fun fromDtoToEntity(dto: CommentDto?): Comment {
        return Comment(
            id = dto?.id,
            dateCommented = dto?.dateCommented,
            content = dto?.content,
            postId = Post(dto?.postId),
            userId = UserAuth(dto?.userId),
            likes = dto?.likes,
            dislikes = dto?.dislikes
        )
    }

    fun fromEntityToDto(entity: Comment?): CommentDto {
        return CommentDto(
            id = entity?.id,
            dateCommented = entity?.dateCommented,
            content = entity?.content,
            postId = entity?.postId?.id,
            userId = entity?.userId?.id,
            likes = entity?.likes,
            dislikes = entity?.dislikes,
        )
    }

    fun fromEntityListToDtoList(entityList : List<Comment>?): List<CommentDto>? {
        return if(entityList.isNullOrEmpty()){
            val listOfCommentDto : MutableList<CommentDto> = emptyList<CommentDto>().toMutableList()
            if(entityList?.isEmpty() == false){
                entityList.forEach {
                    listOfCommentDto.add(fromEntityToDto(it))
                  }
            }
            listOfCommentDto
        }else{
            null
        }
    }
}