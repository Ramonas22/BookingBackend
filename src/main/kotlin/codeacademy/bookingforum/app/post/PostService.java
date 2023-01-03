package codeacademy.bookingforum.app.post;

import codeacademy.bookingforum.app.photo.Photo;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    PostMapper postMapper;


    public PostDto findById(Long id) {
        Post post = postRepo.findById(id).orElse(null);
        return postMapper.toDto(post);
    }

    public List<PostDto> findAllPosts() {
        List<Post> posts = (List<Post>) postRepo.findAll();
        return postMapper.toDtos(posts);
    }

    public PostDto createPost(PostDto postDto) {
        Post post = postMapper.fromDto(postDto);
        postRepo.save(post);
        return postMapper.toDto(post);
    }

    public String updatePost(Long id, PostDto postDto) {
        if(postRepo.existsById(id)){
            postRepo.save(
                    new Post(
                            id,
                            postDto.getTitle(),
                            postDto.getContent(),
                            postDto.getDatePosted(),
                            postDto.getPhotoPost_id().stream().map( ids -> new Photo(ids)).toList(),
                            new UserAuth(postDto.getUser_id())
                    ));
            return "Post with id " + id + " updated";
        }else {
            return "Post with id " + id + " does not exist";
        }
    }

    public String deletePost(Long id) {
        if (postRepo.existsById(id)) {
            postRepo.deleteById(id);
            return "Post with id " + id + " deleted";
        } else {
            return "Post with id " + id + " does not exist";
        }
    }


}
