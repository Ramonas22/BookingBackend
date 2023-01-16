package codeacademy.bookingforum.app.user.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAuthRepo extends JpaRepository<UserAuth, Long> {
    UserAuth findByUsername(String username);
    UserAuth findByEmail(String email);

    @Query(value = "select id from user_auth where seller_id IS NOT NULL", nativeQuery = true)
    List<Long> getActiveSellerIds();

}
