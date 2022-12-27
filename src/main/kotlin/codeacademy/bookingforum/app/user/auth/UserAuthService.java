package codeacademy.bookingforum.app.user.auth;

import codeacademy.bookingforum.app.ecxeption.user.UserNotFoundException;
import codeacademy.bookingforum.app.user.role.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;
@Service
public class UserAuthService {
    @Autowired
    UserAuthMapper userAuthMapper;
    @Autowired
    UserAuthRepo userAuthRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    // REGEX (Requirements below)
    /*
    Username consists of alphanumeric characters (a-z, A-Z, 0-9), lowercase or uppercase.
    Username allows usage of underscore (_), and hyphen (-).
    The underscore (_), or hyphen (-) must not be the first or last character.
    The underscore (_), or hyphen (-) does not appear consecutively, e.g., user__name
    Username length must be 5 to 25 characters total.
     */
    private static final Pattern pattern = Pattern.compile("^[a-zA-Z0-9]([_-](?![_-])|[a-zA-Z0-9]){3,23}[a-zA-Z0-9]$");

    public ResponseEntity<String> createUser(UserAuthDto newUser) {
        if(newUser == null) {
            throw new UserNotFoundException("Invalid request. No user defined.");
        } else if (newUser.getUsername() == null || newUser.getEmail() == null) {
            // change
            return new ResponseEntity<>("sss",HttpStatus.BAD_REQUEST);
        }
        userAuthRepo.save(userAuthMapper.fromDto(newUser));
        // change
        return new ResponseEntity<>("sss",HttpStatus.BAD_REQUEST);
    }
    public UserAuthDto createSeller(UserAuthDto user) {
        if(user == null) {
            return null;
        }
        userAuthRepo.save(userAuthMapper.fromDtoSeller(user));
        return user;
    }
    public UserAuthDto getUser(Long id) {
        UserAuth user = userAuthRepo.findById(id).orElseThrow(() -> new UserNotFoundException("Could not find user with id "+id));
        return userAuthMapper.toDto(user);
    }
    public List<UserAuth> userList() {
        return (List<UserAuth>)userAuthRepo.findAll();
    }
}
