package codeacademy.bookingforum.app.user.auth;

import codeacademy.bookingforum.app.configuration.ResponseObject;
import codeacademy.bookingforum.app.ecxeption.global.InvalidRequestException;
import codeacademy.bookingforum.app.ecxeption.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class UserAuthService {
    @Autowired
    UserAuthMapper userAuthMapper;
    @Autowired
    UserAuthRepo userAuthRepo;

    /* USERNAME REGEX (Requirements below)
    Username consists of alphanumeric characters (a-z, A-Z, 0-9) lowercase or uppercase, also includes underscore (_) and hyphen (-).
    The underscore (_), or hyphen (-) must not be the first or last character and does not appear consecutively, e.g., user__name.
    Username length must be 5 to 25 characters total.*/
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9]([_-](?![_-])|[a-zA-Z0-9]){3,23}[a-zA-Z0-9]$");

    /* PASSWORD REGEX (Requirements below)
    Password must contain at least one digit [0-9], lowercase letter [a-z], uppercase letter [A-Z].
    Password must contain at least one special character like ! @ # & ( ) (no square brackets).
    Password length must be 8 to 30 characters total.*/
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{8,30}$");
    private static boolean validateRegex(final String username, Pattern pattern) {
        Matcher matcher = pattern.matcher(username);
        return !matcher.matches();
    }

    public ResponseObject createUser(UserAuthDto user, WebRequest request) {
        if(ValidateUser(user)) {
            UserAuth newUser = userAuthRepo.save(userAuthMapper.fromDto(user));
            return new ResponseObject("User "+newUser.getUsername()+" was created successfully.",request);
        } else {
            throw new InvalidRequestException("Invalid request. Something went wrong.");
        }
    }
    public ResponseObject createSeller(UserAuthDto seller, WebRequest request) {
        if(ValidateUser(seller)) {
            UserAuth newSeller = userAuthRepo.save(userAuthMapper.fromDtoSeller(seller));
            ResponseObject response = new ResponseObject("User "+newSeller.getUsername()+" was created successfully.",request);
            response.getMessages().add("An admin will review your request within 24 hours. We will send you an email with updates.");
            return response;
        } else {
            throw new InvalidRequestException("Invalid request. Something went wrong.");
        }
    }
    public UserManagementDto getUserById(Long id) {
        UserAuth user = userAuthRepo.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("Could not find user with id "+id);
        } return userAuthMapper.toDtoManagement(user);
    }
    public UserManagementDto getUserByUsername(String username) {
        UserAuth user = userAuthRepo.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("Could not find user with username "+username);
        } return userAuthMapper.toDtoManagement(user);
    }
    public List<UserAuth> userList() {
        return userAuthRepo.findAll();
    }

    // Checking if DTO is null, if username or email are taken, if password and repeatPassword match, if format complies with regex
    private boolean ValidateUser (UserAuthDto user) {
        if(user == null) {
            throw new UserNotFoundException("Invalid request. No user defined.");
        } else if (userAuthRepo.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistsException("Username is taken.");
        } else if (userAuthRepo.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExistsException("User with this email already exists.");
        } else if (!user.getPassword().equals(user.getRepeatPassword())) {
            throw new PasswordMismatchException("Passwords don't match.");
        } else if (validateRegex(user.getUsername(), USERNAME_PATTERN)) {
            throw new UsernameFormatException("Username does not match regex.");
        } else if (validateRegex(user.getPassword(), PASSWORD_PATTERN)) {
            throw new PasswordFormatException("Password does not match regex.");
        } else {
            return true;
        }
    }
}
