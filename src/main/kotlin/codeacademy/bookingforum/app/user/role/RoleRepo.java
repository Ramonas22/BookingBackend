package codeacademy.bookingforum.app.user.role;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepo  extends CrudRepository<Role, Long> {
    Role findByRole(RoleEnum displayName);
}
