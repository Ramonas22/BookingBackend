package codeacademy.bookingforum.app.comment

import java.time.LocalDateTime

data class CommentDto(
    val id : Long? = null,
    val dateCommented : LocalDateTime? = null,
    val content : String? = "",
    val postId : Int? = null,
    val userId : Int? = null,
    val likes : Int? = 0,
    val dislikes : Int? = 0,
)