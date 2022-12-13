package code.Academy.BookingApp.user.auth;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepo extends JpaRepository<UserAuth, Long> {

}
