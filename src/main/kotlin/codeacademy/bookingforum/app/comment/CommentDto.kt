package codeacademy.bookingforum.app.comment

import java.time.LocalDateTime

data class CommentDto(
    val id : Long? = null,
    val dateCommented : LocalDateTime? = null,
    val content : String? = "",
    val postId : Long? = null,
    val userId : Long? = null,
    val likes : Int? = 0,
    val dislikes : Int? = 0,
)