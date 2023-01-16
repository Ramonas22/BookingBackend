package codeacademy.bookingforum.app.user.auth;

import codeacademy.bookingforum.app.configuration.ResponseObject;
import codeacademy.bookingforum.app.user.auth.dto.UserAuthDto;
import codeacademy.bookingforum.app.user.auth.dto.UserDetailsDto;
import codeacademy.bookingforum.app.user.auth.dto.UserLoginDto;
import codeacademy.bookingforum.app.user.auth.dto.UserManagementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserAuthController {
    // ### Services ###
    @Autowired
    UserAuthService userAuthService;

    // ### Endpoints ###

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED) // Returns status 201, if successful
    public ResponseObject register(@RequestBody @Valid UserAuthDto newUser, WebRequest request) {
        return userAuthService.register(newUser, request);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDetailsDto> login(@RequestBody @Valid UserLoginDto loginObject) {
        return userAuthService.login(loginObject);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/get/userlist")
    public List<UserManagementDto> userList() {
        return userAuthService.userList();
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/get/username/{username}")
    public UserManagementDto getUserByUsername(@PathVariable("username") String username) {
        return userAuthService.getUserByUsername(username);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/get/id/{id}")
    public UserManagementDto getUserById(@PathVariable("id") Long id) {
        return userAuthService.getUserById(id);
    }

    @Secured({"ROLE_USER","ROLE_SELLER","ROLE_ADMIN"})
    @PutMapping("/update/{username}")
    @ResponseStatus(HttpStatus.ACCEPTED) // Returns status 202, if successful
    public ResponseObject update(@PathVariable("username") String username, @RequestBody UserDetailsDto dto, WebRequest request) {
        return userAuthService.update(username, dto, request);
    }

    @GetMapping("/details/{requested}/{requesting}")
    public UserDetailsDto details(@PathVariable("requested") String requestedUser, @PathVariable("requesting") String requestingUser) {
        return userAuthService.details(requestedUser, requestingUser);
    }
}
