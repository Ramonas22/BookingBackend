package codeacademy.bookingforum.app.image;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ImageDto {
    private String username;
    private List<String> tags;
    private String description;
    private String type;
    private String location;
    private String name;

}
