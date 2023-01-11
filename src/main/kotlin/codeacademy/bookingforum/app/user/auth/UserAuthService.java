package codeacademy.bookingforum.app.user.auth;

import codeacademy.bookingforum.app.configuration.JwtUtils;
import codeacademy.bookingforum.app.configuration.ResponseObject;
import codeacademy.bookingforum.app.configuration.UserDetailsImpl;
import codeacademy.bookingforum.app.ecxeption.global.InvalidRequestException;
import codeacademy.bookingforum.app.ecxeption.user.*;
import codeacademy.bookingforum.app.enums.Gender;
import codeacademy.bookingforum.app.user.auth.dto.UserAuthDto;
import codeacademy.bookingforum.app.user.auth.dto.UserDetailsDto;
import codeacademy.bookingforum.app.user.auth.dto.UserLoginDto;
import codeacademy.bookingforum.app.user.auth.dto.UserManagementDto;

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
    // Services
    @Autowired
    UserAuthMapper userAuthMapper;
    @Autowired
    UserAuthRepo userAuthRepo;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;

    /* USERNAME REGEX (Requirements below)
    Username consists of alphanumeric characters (a-z, A-Z, 0-9) lowercase or uppercase, also includes underscore (_) and hyphen (-).
    The underscore (_), or hyphen (-) must not be the first or last character and does not appear consecutively, e.g., user__name.
    Username length must be 5 to 25 characters total.*/
    public static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9]([_-](?![_-])|[a-zA-Z0-9]){3,23}[a-zA-Z0-9]$");

    /* PASSWORD REGEX (Requirements below)
    Password must contain at least one digit [0-9], lowercase letter [a-z], uppercase letter [A-Z].
    Password must contain at least one special character like ! @ # & ( ) (no square brackets).
    Password length must be 8 to 30 characters total.*/
    public static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{8,30}$");

    // ### Endpoint services ###

    // @ Sign Up (creates a user account)
    public ResponseObject register(UserAuthDto user, WebRequest request) throws InvalidRequestException {
        if (validateUser(user)) {
            userAuthRepo.save(userAuthMapper.fromDtoUser(user));
            return new ResponseObject(Collections.singletonList("User "+user.getUsername()+" was created successfully."),HttpStatus.CREATED, request);
        }
        throw new InvalidRequestException("Invalid request. Something went wrong.");
    }

    // @ Sign In (authenticates existing user)
    public ResponseEntity<UserDetailsDto> login(UserLoginDto loginObject) throws InvalidRequestException {
        UserAuth user;

        // fieldType defines what was provided for authorization (username or email)
        String fieldType;

        if (loginObject.getUnameOrEmail().contains("@")) {
            user = userAuthRepo.findByEmail(loginObject.getUnameOrEmail());
            fieldType = "email ";
        } else {
            user = userAuthRepo.findByUsername(loginObject.getUnameOrEmail());
            fieldType = "username ";
        }

        // Check if user exists, and if their account is active or not. Authenticate if account is active, decline request otherwise (FORBIDDEN, status 403)
        if (user == null) {
            throw new UserNotFoundException("User with "+fieldType+loginObject.getUnameOrEmail()+" does not exist.");
        } else if (!user.isEnabled()) {
            // Thrown when a seller account is not activated
            throw new AccountNotActivatedException("Our Admins have not yet activated your account! Please wait until we approve your request.");
        } else {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), loginObject.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            String jwtToken = jwtUtils.generateJwtToken(userDetails);

            UserDetailsDto response = userAuthMapper.toDtoDetails(user);
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwtToken).body(response);
        }
    }

    // @ Get a list of all users
    public List<UserManagementDto> userList() {
        List<UserManagementDto> dtos = new ArrayList<>();
        userAuthRepo.findAll().forEach(user -> dtos.add(userAuthMapper.toDtoManagement(user)));
        return dtos;
    }

    // @ Get one user by id
    public UserManagementDto getUserById(Long id) {
        UserAuth user = userAuthRepo.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("Could not find user with id "+id);
        } return userAuthMapper.toDtoManagement(user);
    }

    // @ Get one user by username
    public UserManagementDto getUserByUsername(String username) {
        UserAuth user = userAuthRepo.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("Could not find user with username "+username);
        } return userAuthMapper.toDtoManagement(user);
    }

    // @ Update user's properties
    public ResponseObject update(String username, UserDetailsDto dto, WebRequest request) {
        if (username == null || dto == null) {
            throw new UserNotFoundException("No user provided!");
        }
        if (dto.getPassword() != null) { // If a user provided a new password to be updated, validate it against a pre-defined regex pattern
            if (validateRegex(dto.getPassword(), PASSWORD_PATTERN)) {
                throw new PasswordFormatException("Password does not match regex."); // More messages are added in UserExceptionHandler.class #passwordFormatHandler
            }
        }

        UserAuth user = userAuthRepo.findByUsername(username);
        if (user == null || !user.getId().equals(dto.getId()) || !user.getUsername().equals(dto.getUsername())) {
            throw new InvalidRequestException("Requesting user and user to be updated do not match!");
        }

        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setBiography(dto.getBiography());
        user.setGender(Gender.parse(dto.getGender()));
        userAuthRepo.save(user);

        return new ResponseObject(Collections.singletonList("User successfully updated!"), HttpStatus.ACCEPTED, request);
    }

    // @ Get user's details, to display in their account profile
    public UserDetailsDto details(String requestedUser, String requestingUser) {
        UserAuth user = userAuthRepo.findByUsername(requestedUser);
        if (user == null) {
            throw new UserNotFoundException("User with username "+requestedUser+" does not exist.");
        }
        if (requestingUser == null) {requestingUser = "none";}
        UserDetailsDto dto = userAuthMapper.toDtoDetails(user);
        // If the requesting user requests someone else's profile, some fields will be set to null
        // This is easy to overcome in PostMan, when the performing person knows the outcome and prerequisites
        if (!requestedUser.equals(requestingUser)) {
            dto.setEmail(null);
            dto.setGender(null);
            dto.setBiography(null);
        }
        return dto;
    }

    // ### Validations and checks ###

    // Checking if DTO is null, if username or email are taken, if password and repeatPassword match, if username and password formats comply with regex
    // Http Status Codes are set individually for every type of exception in UserExceptionHandler.class
    private boolean validateUser(UserAuthDto user) {
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

    // Method to validate if a given String matches a given regex pattern.
    private static boolean validateRegex(final String str, Pattern pattern) {
        Matcher matcher = pattern.matcher(str);
        return !matcher.matches();
    }
}
