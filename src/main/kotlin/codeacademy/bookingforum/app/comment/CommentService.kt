package codeacademy.bookingforum.app.comment

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentService {
    @Autowired
    private lateinit var mapper: CommentMapper

    @Autowired
    private lateinit var repository: CommentRepository

    fun getAllComments(): List<CommentDto>? {
        return mapper.fromEntityListToDtoList( repository.findAll().toList() )
    }

    fun getCommentById(id: Long): CommentDto? {
        return mapper.fromEntityToDto( repository.findByIdOrNull(id))
    }

    fun postComment(commentDto: CommentDto?){
        mapper.fromDtoToEntity(commentDto).let { repository.save(it) }
    }

    fun updateComment(id: Long, commentDto: CommentDto?) {
        mapper.fromDtoToEntity(
            CommentDto(
                id = id,
                dateCommented = commentDto?.dateCommented,
                content = commentDto?.content,
                postId = commentDto?.postId,
                userId = commentDto?.userId,
                likes = commentDto?.likes,
                dislikes = commentDto?.dislikes,
            )
        ).let { repository.save(it) }
    }

    fun deleteComment(id: Long): String {
        return if(repository.existsById(id)){
            repository.deleteById(id)
            "Deleted user with id $id"
        }else{
            "Id not found"
        }
    }

}