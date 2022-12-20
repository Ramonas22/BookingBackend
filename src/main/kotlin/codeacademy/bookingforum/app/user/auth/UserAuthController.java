package codeacademy.bookingforum.app.user.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000","http://127.0.0.1:3000"})
@RequestMapping("/api/user")
public class UserAuthController {
    @Autowired
    UserAuthService userAuthService;

    @PostMapping("/create/user")
    public UserAuthDto createUser(@RequestBody UserAuthDto user) {
        return userAuthService.createUser(user);
    }
    @PostMapping("/create/seller")
    public UserAuthDto createSeller(@RequestBody UserAuthDto user) {
        return userAuthService.createSeller(user);
    }
    @GetMapping("/get/{id}")
    public UserAuthDto getUser(@PathVariable("id") Long id) {
        return userAuthService.getUser(id);
    }
    @GetMapping("/get/userlist")
    public List<UserAuth> userList() {
        return userAuthService.userList();
    }
}
