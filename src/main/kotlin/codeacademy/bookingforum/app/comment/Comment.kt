package codeacademy.bookingforum.app.comment

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "comment")
data class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "date_commented")
    val dateCommented: LocalDateTime? = null,

    @Column(name = "content")
    val content: String? = "",

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @Column(name = "post_id")
    val postId: Int? = null,

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Column(name = "user_id")
    val userId: Int? = null,

    @Column(name = "likes")
    val likes: Int? = 0,

    @Column(name = "dislikes")
    val dislikes: Int? = 0,
)
