package codeacademy.bookingforum.app.comment

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/comment")
class CommentController {

    @Autowired
    private lateinit var service: CommentService

    @GetMapping("/")
    fun getComment(): List<CommentDto>?{
        return service.getAllComments()
    }

    @GetMapping("/{id}")
    fun getCommentById(@PathVariable id : Long): CommentDto?{
        return service.getCommentById(id)
    }

    @PostMapping("/")
    fun postComment(@RequestBody commentDto: CommentDto?){
        return service.postComment(commentDto)
    }

    @PutMapping("/{id}")
    fun updatePost(@RequestBody commentDto: CommentDto?, @PathVariable id: Long){
        return service.updateComment(id, commentDto)
    }

    @DeleteMapping("/{id}")
    fun deleteComment(@PathVariable id: Long): String{
        return service.deleteComment(id)
    }

}