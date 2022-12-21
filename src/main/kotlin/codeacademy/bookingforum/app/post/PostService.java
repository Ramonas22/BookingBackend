package codeacademy.bookingforum.app.post;

import codeacademy.bookingforum.app.photo.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    PostMapper postMapper;



    public PostDto findById(Long id){
        Post post = postRepo.findById(id).orElse(null);
        return postMapper.toDto(post);
    }

    public List<PostDto> findAllPosts(){
        List<Post> posts = (List<Post>) postRepo.findAll();
        return postMapper.toDtos(posts);
    }

    public PostDto createPost(PostDto postDto){
        Post post = postMapper.fromDto(postDto);
        postRepo.save(post);
        return postMapper.toDto(post);
    }

    public PostDto updatePost(PostDto postDto){
        Post post = postMapper.fromDto(postDto);
        postRepo.save(post);
        return postMapper.toDto(post);
    }

    public void deletePost(Long id){
        postRepo.deleteById(id);
    }




}
