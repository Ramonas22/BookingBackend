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

    private List<String> images;

    private Long user_id;

    public PostDto(Long id, String title, String content, Date datePosted, List<String> images, Long user_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.datePosted = datePosted;
        this.images = images;
        this.user_id = user_id;
    }
}
