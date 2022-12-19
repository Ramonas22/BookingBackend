package codeacademy.bookingforum.app.comment

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepo: CrudRepository<Comment, Long> {
}