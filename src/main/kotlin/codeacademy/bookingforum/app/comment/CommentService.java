package codeacademy.bookingforum.app.comment;

import codeacademy.bookingforum.app.configuration.ResponseObject;
import codeacademy.bookingforum.app.ecxeption.global.UnsatisfiedExpectationException;
import codeacademy.bookingforum.app.ecxeption.user.UserNotFoundException;
import codeacademy.bookingforum.app.post.Post;
import codeacademy.bookingforum.app.post.PostRepo;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import codeacademy.bookingforum.app.user.auth.UserAuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepo;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserAuthRepo userRepo;
    @Autowired
    PostRepo postRepo;

    public ResponseObject create(CommentDto comment, WebRequest request) {
        UserAuth user = userRepo.findById(comment.getUserId()).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User with id "+comment.getUserId()+" does not exist!");
        }
        checkAuth(user);
        commentRepo.save(commentMapper.fromDto(comment));

        return new ResponseObject(Collections.singletonList("Comment successfully published."), HttpStatus.CREATED, request);
    }

    public List<Long> getList(Long id) {
        Post post = postRepo.findById(id).orElse(null);
        if (post == null) {
            throw new UnsatisfiedExpectationException("Can't get comments of non-existing Post");
        }

        return commentRepo.findAllIdByPost(post.getId());
    }

    public CommentDto getById(Long id) {
        Comment comment = commentRepo.findById(id).orElse(null);
        if (comment == null) {
            throw new UnsatisfiedExpectationException("Comment with id "+id+" does not exist!");
        }

        return commentMapper.toDto(comment);
    }

    // Validate authorization, check whether user is requesting his own resource
    void checkAuth(UserAuth user) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!Objects.equals(userDetails.getUsername(), user.getUsername())) {
            throw new UnsatisfiedExpectationException("Requesting and requested user do not match!");
        }
    }
}
