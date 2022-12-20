package codeacademy.bookingforum.app.configuration;

import codeacademy.bookingforum.app.user.auth.UserAuth;
import codeacademy.bookingforum.app.user.auth.UserAuthRepo;
import codeacademy.bookingforum.app.user.enums.Gender;
import codeacademy.bookingforum.app.user.role.Role;
import codeacademy.bookingforum.app.user.role.RoleRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

// Executes tasks when the program starts. Creates default Admin user and two default roles (ROLE_USER and ROLE_ADMIN)
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

        createUserIfNotFound("Admin","admin@irenteye.com","h5H5n7DSV$aT4D^S^9Wq");

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
    @Transactional
    UserAuth createUserIfNotFound(String name, String email, String password) {
        UserAuth user = userRepository.findByUsername(name);
        if (user == null) {
            user = new UserAuth();
            user.setUsername(name);
            user.setGender(Gender.UNDEFINED);
            user.setEmail(email);
            user.setEnabled(true);
            user.setPassword(passwordEncoder.encode(password));
            user.setRoles(Collections.singletonList(roleRepository.findByDisplayName("ROLE_ADMIN")));

            userRepository.save(user);
        }
        return user;
    }
}
