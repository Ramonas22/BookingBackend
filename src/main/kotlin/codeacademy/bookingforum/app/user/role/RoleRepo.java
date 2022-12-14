package codeacademy.bookingforum.app.user.role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo  extends JpaRepository<Role, Long> {
    Role findByDisplayName(String displayName);
}
