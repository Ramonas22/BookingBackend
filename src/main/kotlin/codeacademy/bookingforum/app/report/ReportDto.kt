package codeacademy.bookingforum.app.report

import java.time.LocalDateTime

data class ReportDto(
    val id: Long? = null,

    val reason: String? = "",

    val description: String? = "",

    val reportDate: LocalDateTime? = null,

    val commentId: Long? = null,

    val userID: Long? = null,

    val postId: Long? = null,
)
