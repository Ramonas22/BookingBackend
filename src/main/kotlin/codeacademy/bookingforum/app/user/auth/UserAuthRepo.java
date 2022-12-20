package codeacademy.bookingforum.app.user.auth;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepo extends CrudRepository<UserAuth, Long> {
    UserAuth findByUsername(String username);
}
