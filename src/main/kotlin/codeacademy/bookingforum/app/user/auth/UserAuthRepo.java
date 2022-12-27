package codeacademy.bookingforum.app.user.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepo extends JpaRepository<UserAuth, Long> {
    UserAuth findByUsername(String username);
}
