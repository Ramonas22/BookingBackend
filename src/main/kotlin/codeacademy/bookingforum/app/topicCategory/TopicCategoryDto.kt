package codeacademy.bookingforum.app.topicCategory

data class TopicCategoryDto(
    val id: Long? = null,
    val title: String? = "",
    val description: String? = "",
    val role: List<String>?,
    val topics: List<Long>?,
)
