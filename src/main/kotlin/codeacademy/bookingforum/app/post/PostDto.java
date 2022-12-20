package codeacademy.bookingforum.app.post;

import lombok.*;

import java.util.Date;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    private Long id;

    private String title;

    private String content;

    private Date datePosted;

    private List<Long> photoPost_id;

    private Long user_id;

}
