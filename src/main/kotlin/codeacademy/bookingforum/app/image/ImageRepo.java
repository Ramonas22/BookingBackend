package codeacademy.bookingforum.app.image;

import codeacademy.bookingforum.app.user.auth.UserAuth;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepo extends CrudRepository<Image, Long> {
    Image findByType(ImageType type);
    Image findByUser(UserAuth user);
    List<Image> findAllByUserAndType(UserAuth user, ImageType type);

    @Query(value = "SELECT location FROM image WHERE image.user_image = :id AND image.type = 'SELLER_GALLERY' ORDER BY RAND() LIMIT 1", nativeQuery = true)
    String findOneBySellerId(@Param("id") Long id);
}
