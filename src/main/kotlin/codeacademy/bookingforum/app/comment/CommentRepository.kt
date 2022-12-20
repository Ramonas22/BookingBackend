package codeacademy.bookingforum.app.comment

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository: CrudRepository<Comment, Long> {
}