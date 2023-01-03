package codeacademy.bookingforum.app.configuration;

import codeacademy.bookingforum.app.user.auth.UserAuth;
import codeacademy.bookingforum.app.user.auth.UserAuthRepo;
import codeacademy.bookingforum.app.enums.Gender;
import codeacademy.bookingforum.app.user.role.Role;
import codeacademy.bookingforum.app.user.role.RoleEnum;
import codeacademy.bookingforum.app.user.role.RoleRepo;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

// Executes tasks when the program starts. Creates default Admin user and two default roles (ROLE_USER and ROLE_ADMIN)
@Component
public class SetupDataOnStartup implements
        ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    protected UserAuthRepo userRepository;

    @Autowired
    protected RoleRepo roleRepository;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    /*
    - "alreadySetup" variable is used because ContextRefreshedEvent may be activated several times.
    - Checks if initial privileges and roles exist and, if not, creates them.
    - Creates and saves an admin account, assigns appropriate role and privileges. (Will override existing Admin account on each startup/restart).
    */
    @Override
    @Transactional
    public void onApplicationEvent(@NotNull ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        createRoleIfNotFound(RoleEnum.ROLE_ADMIN);
        createRoleIfNotFound(RoleEnum.ROLE_USER);
        createUserIfNotFound();

        alreadySetup = true;
    }

    @Transactional
    void createRoleIfNotFound(RoleEnum name) {

        Role role = roleRepository.findByRole(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
    }

    @Transactional
    void createUserIfNotFound() {
        UserAuth user = userRepository.findByUsername("Admin");
        if (user == null) {
            user = new UserAuth();
            user.setUsername("Admin");                                                      // Username
            user.setGender(Gender.UNDEFINED);                                               // Gender
            user.setEmail("admin@irenteye.com");                                            // Email
            user.setEnabled(true);                                                          // Is account enabled?
            user.setPassword(passwordEncoder.encode("h5H5n7DSV$aT4D^S^9Wq"));    // Password
            user.setRoles(user.getRoles());   // Role

            userRepository.save(user);
        }
    }
}
