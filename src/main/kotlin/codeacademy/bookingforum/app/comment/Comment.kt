package codeacademy.bookingforum.app.comment

import codeacademy.bookingforum.app.post.Post
import codeacademy.bookingforum.app.user.auth.UserAuth
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "comment")
data class Comment(
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
    val id: Long? = null,

        @Column(name = "date_commented")
    val dateCommented: LocalDateTime? = null,

        @Column(name = "content")
    val content: String? = "",

        @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    val postId: Post? = null,

        @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val userId: UserAuth? = null,

        @Column(name = "likes")
    val likes: Int? = 0,

        @Column(name = "dislikes")
    val dislikes: Int? = 0,
)
