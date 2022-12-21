package codeacademy.bookingforum.app.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;


    @PostMapping
    public PostDto addPost(@RequestBody PostDto postDto) {
        return postService.createPost(postDto);
    }

    @GetMapping("/{id}")
    public PostDto getPhoto(@PathVariable(name = "id") Long id) {
        return postService.findById(id);
    }

    @GetMapping
    public List<PostDto> getAllPhotos(){return postService.findAllPosts();}

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable(name = "id") Long id){
        postService.deletePost(id);
    }

    @PutMapping("/{id}")
    public PostDto updatePhoto(@RequestBody PostDto postDto, @PathVariable(name = "id") Long id){
        return postService.updatePost(postDto);
    }


}
