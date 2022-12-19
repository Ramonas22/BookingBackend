package codeacademy.bookingforum.app.comment

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentService {
    @Autowired
    private lateinit var mapper: CommentMapper

    @Autowired
    private lateinit var repo: CommentRepo

    fun getAllComments(): List<CommentDto>? {
        return mapper.fromEntityListToDtoList( repo.findAll().toList() )
    }

    fun getCommentById(id: Long): CommentDto? {
        return mapper.fromEntityToDto( repo.findByIdOrNull(id))
    }

    fun postComment(commentDto: CommentDto?){
        mapper.fromDtoToEntity(commentDto).let { repo.save(it) }
    }

    fun updateComment(id: Long, commentDto: CommentDto?) {
        mapper.fromDtoToEntity(
            CommentDto(
                id = id,
                date_commented = commentDto?.date_commented,
                content = commentDto?.content,
                post_id = commentDto?.post_id,
                user_id = commentDto?.user_id,
                likes = commentDto?.likes,
                dislikes = commentDto?.dislikes,
            )
        ).let { repo.save(it) }
    }

    fun deleteComment(id: Long): String {
        return if(repo.existsById(id)){
            repo.deleteById(id)
            "Deleted user with id $id"
        }else{
            "Id not found"
        }
    }

}