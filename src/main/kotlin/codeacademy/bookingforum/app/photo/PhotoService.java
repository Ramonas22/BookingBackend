package codeacademy.bookingforum.app.photo;

import codeacademy.bookingforum.app.post.Post;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {

    @Autowired
    PhotoRepo photoRepo;

    @Autowired
    PhotoMapper photoMapper;

    public PhotoDto findById(Long id) {
        Photo photo = photoRepo.findById(id).orElse(null);
        return photoMapper.toDto(photo);
    }

    public List<PhotoDto> findAllPhotos() {
        List<Photo> photos = (List<Photo>) photoRepo.findAll();
        return photoMapper.toDto(photos);
    }


    public PhotoDto createPhoto(PhotoDto photoDto) {
        Photo photo = photoMapper.fromDto(photoDto);
        photoRepo.save(photo);
        return photoMapper.toDto(photo);
    }

    public String updatePhoto(Long id, PhotoDto photoDto) {
        if(photoRepo.existsById(id)) {
            photoRepo.save(
                    new Photo(
                            id,
                            photoDto.getImageUrl(),
                            photoDto.getTags(),
                            photoDto.getDescription(),
                            photoDto.getType(),
                            photoDto.getUserPhotos_id().stream().map( ids -> new UserAuth(ids) ).toList(),
                            photoDto.getPostPhoto_id().stream().map( ids -> new Post(ids)).toList()));
            return "Photo with id " + id + " updated";
        }else {
            return "Photo with id " + id + " not found";
        }
    }

    public String deletePhoto(Long id) {
        if (photoRepo.existsById(id)) {
            photoRepo.deleteById(id);
            return "Photo with id " + id + " deleted";
        } else {
            return "Photo with id " + id + " does not exist";
        }
    }


}
