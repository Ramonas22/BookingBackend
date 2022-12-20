package codeacademy.bookingforum.app.report

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "report")
data class Report(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "reason")
    val reason: String? = "",

    @Column(name = "description")
    val description: String? = "",

    @Column(name = "report_date")
    val reportDate: LocalDateTime? = null,

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    @Column(name = "comment_id")
    val commentId: Long? = null,

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Column(name = "user_id")
    val userID: Long? = null,

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @Column(name = "post_id")
    val postId: Long? = null,

)
