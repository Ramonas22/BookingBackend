package codeacademy.bookingforum.app.image;

import codeacademy.bookingforum.app.user.auth.UserAuth;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImageRepo extends CrudRepository<Image, Long> {
    Image findByType(ImageType type);
    Image findByUser(UserAuth user);
    List<Image> findAllByUserAndType(UserAuth user, ImageType type);
}
