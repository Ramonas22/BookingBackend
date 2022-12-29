package codeacademy.bookingforum.app.user.auth;

import codeacademy.bookingforum.app.configuration.JwtUtils;
import codeacademy.bookingforum.app.configuration.ResponseObject;
import codeacademy.bookingforum.app.configuration.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
    public ResponseObject createUser(@RequestBody @Valid UserAuthDto user, WebRequest request) {
        return userAuthService.createUser(user, request);
    }
    @PostMapping("/register/seller")
    public ResponseObject createSeller(@RequestBody @Valid UserAuthDto seller, WebRequest request) {
        return userAuthService.createSeller(seller, request);
    }
    @GetMapping("/get/id/{id}")
    public ResponseObject getUserById(@PathVariable("id") Long id, WebRequest request) {
        return userAuthService.getUserById(id, request);
    }
    @GetMapping("/get/username/{username}")
    public ResponseObject getUserByUsername(@PathVariable("username") String username, WebRequest request) {
        return userAuthService.getUserByUsername(username, request);
    }
    @GetMapping("/get/userlist")
    public List<UserAuth> userList() {
        return userAuthService.userList();
    }
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserAuthDto loginObject) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginObject.getUsername(), loginObject.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwtToken = jwtUtils.generateJwtToken(userDetails);
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwtToken).build();
    }
}
