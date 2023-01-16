package codeacademy.bookingforum.app.post;

import codeacademy.bookingforum.app.configuration.ResponseObject;
import codeacademy.bookingforum.app.ecxeption.global.UnsatisfiedExpectationException;
import codeacademy.bookingforum.app.ecxeption.user.UserNotFoundException;
import codeacademy.bookingforum.app.image.FileStorageService;
import codeacademy.bookingforum.app.image.ImageDto;
import codeacademy.bookingforum.app.image.ImageType;
import codeacademy.bookingforum.app.topic.Topic;
import codeacademy.bookingforum.app.topic.TopicRepository;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import codeacademy.bookingforum.app.user.auth.UserAuthRepo;
import com.google.gson.Gson;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class PostService {
    @Autowired
    PostRepo postRepo;
    @Autowired
    PostMapper postMapper;
    @Autowired
    UserAuthRepo userRepo;
    @Autowired
    FileStorageService storageService;
    @Autowired
    TopicRepository topicRepo;

    @Transactional
    public ResponseObject create(MultipartFile file, String postString, WebRequest request) throws IOException {
        Gson gson = new Gson();
        @Valid NewPostDto postDto = gson.fromJson(postString, NewPostDto.class);

        UserAuth user = userRepo.findById(postDto.getUserId()).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User with id "+postDto.getUserId()+" does not exist!");
        }
        checkAuth(user);

        ImageDto image = new ImageDto();
        image.setUsername(user.getUsername());
        image.setType(ImageType.POST.toString());

        Post post = postMapper.fromNewDto(postDto);
        post.setDatePosted(LocalDateTime.now());

        try {post.setImage(storageService.storePostImage(file, image, user));
        } catch (Exception e) {post.setImage(null);}
        postRepo.save(post);

        return new ResponseObject(Collections.singletonList("Posted successfully."), HttpStatus.CREATED, request);
    }

    public List<PostDto> getList(Long id) {
        Topic topic = topicRepo.findById(id).orElse(null);
        if (topic == null) {
            throw new UnsatisfiedExpectationException("Can't get posts from non-existing topic!");
        }

        List<Post> posts = postRepo.findAllByTopic(topic);
        if (posts == null) {
            throw new UnsatisfiedExpectationException("This topic doesn't have any posts yet!");
        }

        return postMapper.toDtoList(posts);
    }


    // Validate authorization, check whether user is requesting his own resource
    void checkAuth(UserAuth user) {
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (!Objects.equals(userDetails.getUsername(), user.getUsername())) {
        throw new UnsatisfiedExpectationException("Requesting and requested user do not match!");
    }
}

}
