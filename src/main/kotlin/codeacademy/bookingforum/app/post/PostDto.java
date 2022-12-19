package codeacademy.bookingforum.app.post;

import codeacademy.bookingforum.app.photo.Photo;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Long id;

    private String title;

    private String content;

    private Date datePosted;

    private List<Long> photoPost_id;

    private Long user_id;

}
