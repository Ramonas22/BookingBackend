package codeacademy.bookingforum.app.user.auth;

import codeacademy.bookingforum.app.configuration.JwtUtils;
import codeacademy.bookingforum.app.configuration.ResponseObject;
import codeacademy.bookingforum.app.configuration.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000","http://127.0.0.1:3000"})
@RequestMapping("/api/user")
public class UserAuthController {
    @Autowired
    UserAuthService userAuthService;
    @Autowired
    PasswordEncoder passEncoder;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/register/user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject createUser(@RequestBody @Valid UserAuthDto user, WebRequest request) {
        return userAuthService.createUser(user, request);
    }

    @PostMapping("/register/seller")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject createSeller(@RequestBody @Valid UserAuthDto seller, WebRequest request) {
        return userAuthService.createSeller(seller, request);
    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @Secured("ROLE_ADMIN")
    @GetMapping("/get/id/{id}")
    public UserManagementDto getUserById(@PathVariable("id") Long id) {
        return userAuthService.getUserById(id);
    }

//    @Secured("ROLE_ADMIN")
    @GetMapping("/get/username/{username}")
    public UserManagementDto getUserByUsername(@PathVariable("username") String username) {
        return userAuthService.getUserByUsername(username);
    }

//    @Secured("ROLE_ADMIN")
    @GetMapping("/get/userlist")
    public List<UserAuth> userList() {
        return userAuthService.userList();
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(@RequestBody UserLoginDto loginObject, WebRequest request) {
        return userAuthService.login(loginObject, request);
    }
}
