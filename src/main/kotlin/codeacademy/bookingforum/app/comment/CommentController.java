package codeacademy.bookingforum.app.comment;

import codeacademy.bookingforum.app.configuration.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @Secured({"ROLE_ADMIN","ROLE_USER","ROLE_SELLER"})
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject create(@RequestBody CommentDto comment, WebRequest request) {
        return commentService.create(comment, request);
    }

    @GetMapping("/get/list/{id}") // ID of post
    public List<Long> getList(@PathVariable("id") Long id) {
        return commentService.getList(id);
    }

    @GetMapping("/get/{id}") // ID of comment
    public CommentDto getById(@PathVariable("id") Long id) {
        return commentService.getById(id);
    }
}
