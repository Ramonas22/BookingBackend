package code.Academy.BookingApp.configuration;

import code.Academy.BookingApp.user.auth.UserAuth;
import code.Academy.BookingApp.user.auth.UserAuthRepo;
import code.Academy.BookingApp.user.role.Role;
import code.Academy.BookingApp.user.role.RoleRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserAuthRepo userRepository;

    @Autowired
    private RoleRepo roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
    - "alreadySetup" variable is used because ContextRefreshedEvent may be activated several times.
    - Checks if initial privileges and roles exist and, if not, creates them.
    - Creates and saves an admin account, assigns appropriate role and privileges. (Will override existing Admin account on each startup/restart).
    */
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_USER");

        Role adminRole = roleRepository.findByDisplayName("ROLE_ADMIN");
        UserAuth user = new UserAuth();
        user.setUsername("Admin");
        user.setPassword(passwordEncoder.encode("h5H5n7DSV$aT4D^S^9Wq"));
        user.setEmail("admin@janas.lt");
        user.setRoles(Collections.singletonList(adminRole));
        user.setEnabled(true);
        userRepository.save(user);

        alreadySetup = true;
    }

    @Transactional
    Role createRoleIfNotFound(String name) {

        Role role = roleRepository.findByDisplayName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }
}
