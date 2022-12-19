package codeacademy.bookingforum.app.user.auth;

import org.springframework.data.repository.CrudRepository;

public interface UserAuthRepo extends CrudRepository<UserAuth, Long> {
    UserAuth findByDisplayName(String displayName);
}
