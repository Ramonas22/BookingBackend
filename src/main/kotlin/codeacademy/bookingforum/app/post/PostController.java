package codeacademy.bookingforum.app.post;

import codeacademy.bookingforum.app.configuration.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostService postService;

    @Secured({"ROLE_ADMIN","ROLE_USER","ROLE_SELLER"})
    @PostMapping("/create") // ID of posting user
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject create(@RequestParam("file") MultipartFile file, @RequestParam("body") String dtoString, WebRequest request) throws IOException {
        return postService.create(file, dtoString, request);
    }

    @GetMapping("/get/list/{id}") // ID of Post
    public List<PostDto> getList(@PathVariable("id") Long id) {
        return postService.getList(id);
    }
}
