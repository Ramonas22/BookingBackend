package codeacademy.bookingforum.app.comment

import java.util.*

data class CommentDto(
    val id : Long? = null,
    val date_commented : Date? = null,
    val content : String? = "",
    val post_id : Int? = null,
    val user_id : Int? = null,
    val likes : Int? = 0,
    val dislikes : Int? = 0,
)