package codeacademy.bookingforum.app.user.seller.page;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;

public interface SellerPageRepo extends CrudRepository<SellerPage, Long> {

    @Query(value = "select user_roles.user_id as user, image.location as location from user_roles inner join image on image.location", nativeQuery = true)
    Map<Long, String> getPreview();
}
