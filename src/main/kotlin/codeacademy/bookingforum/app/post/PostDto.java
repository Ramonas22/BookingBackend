package codeacademy.bookingforum.app.post;

<<<<<<< HEAD

=======
>>>>>>> 94159c5893cdfd6e952670d8a9aa1713818c87ca
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
