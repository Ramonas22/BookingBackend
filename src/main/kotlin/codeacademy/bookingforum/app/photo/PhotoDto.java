package codeacademy.bookingforum.app.photo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoDto {

    private Long id;

    private String imageUrl;

    private List<String> tags;

    private String description;

    private String type;

    private List<Long> userPhotos_id;

    private List<Long> postPhoto_id;


}
