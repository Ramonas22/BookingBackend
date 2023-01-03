package codeacademy.bookingforum.app.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;


    @PostMapping("/postPost")
    public PostDto addPost(@RequestBody PostDto postDto) {
        return postService.createPost(postDto);
    }

    @GetMapping("/getPost/{id}")
    public PostDto getPhoto(@PathVariable(name = "id") Long id) {
        return postService.findById(id);
    }

    @GetMapping("/getAllPosts")
    public List<PostDto> getAllPhotos(){return postService.findAllPosts();}

    @DeleteMapping("/deletePost/{id}")
    public String deletePost(@PathVariable(name = "id") Long id){
        return postService.deletePost(id);
    }

    @PutMapping("/updatePost/{id}")
    public String updatePhoto(@RequestBody PostDto postDto, @PathVariable(name = "id") Long id){
        return postService.updatePost(id, postDto);
    }


}
