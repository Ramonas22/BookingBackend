package codeacademy.bookingforum.app.user.auth;

import codeacademy.bookingforum.app.configuration.JwtUtils;
import codeacademy.bookingforum.app.configuration.ResponseObject;
import codeacademy.bookingforum.app.configuration.UserDetailsImpl;
import codeacademy.bookingforum.app.ecxeption.global.InvalidRequestException;
import codeacademy.bookingforum.app.ecxeption.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserAuthService {
    @Autowired
    UserAuthMapper userAuthMapper;
    @Autowired
    UserAuthRepo userAuthRepo;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AuthenticationManager authenticationManager;

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
            return new ResponseObject(Collections.singletonList("User "+newUser.getUsername()+" was created successfully."),HttpStatus.CREATED, request);
        } else {
            throw new InvalidRequestException("Invalid request. Something went wrong.");
        }
    }
    public ResponseObject createSeller(UserAuthDto seller, WebRequest request) {
        if(ValidateUser(seller)) {
            UserAuth newSeller = userAuthRepo.save(userAuthMapper.fromDtoSeller(seller));
            ResponseObject response = new ResponseObject(new ArrayList<>(), HttpStatus.CREATED, request);
            response.getMessages().add("User "+newSeller.getUsername()+" was created successfully.");
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

    public ResponseEntity<ResponseObject> login(UserLoginDto loginObject, WebRequest request) {
        UserAuth user;
        String fieldType;
        if (loginObject.getUnameOrEmail().contains("@")) {
            user = userAuthRepo.findByEmail(loginObject.getUnameOrEmail());
            fieldType = "email ";
        } else {
            user = userAuthRepo.findByUsername(loginObject.getUnameOrEmail());
            fieldType = "username ";
        }
        if (user == null) {
            throw new UserNotFoundException("User with "+fieldType+loginObject.getUnameOrEmail()+" does not exist.");
        } else if (!user.isEnabled()) {
            throw new AccountNotActivatedException("Our Admins have not yet activated your account! Please wait until we approve your request.");
        } else {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), loginObject.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            String jwtToken = jwtUtils.generateJwtToken(userDetails);
            ResponseObject response = new ResponseObject(Collections.singletonList("Logged in successfully."), HttpStatus.OK,request);
            //return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwtToken).build();
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwtToken).body(response);
        }
    }

    // Checking if DTO is null, if username or email are taken, if password and repeatPassword match, if username and password formats comply with regex
    // Http Status Codes are set individually for every type of exception in UserExceptionHandler.class
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
            throw new UsernameFormatException("Username does not match regex."); // More messages are added in UserExceptionHandler.class #usernameFormatHandler
        } else if (validateRegex(user.getPassword(), PASSWORD_PATTERN)) {
            throw new PasswordFormatException("Password does not match regex."); // More messages are added in UserExceptionHandler.class #passwordFormatHandler
        } else {
            return true;
        }
    }
}
