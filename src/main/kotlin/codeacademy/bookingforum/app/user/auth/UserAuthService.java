package codeacademy.bookingforum.app.user.auth;

import codeacademy.bookingforum.app.user.role.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class UserAuthService {
    @Autowired
    UserAuthMapper userAuthMapper;
    @Autowired
    UserAuthRepo userAuthRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    public UserAuthDto createUser(UserAuthDto user) {
        if(user == null) {
            return null;
        }
        userAuthRepo.save(userAuthMapper.fromDto(user));
        return user;
    }
    public UserAuthDto createSeller(UserAuthDto user) {
        if(user == null) {
            return null;
        }
        userAuthRepo.save(userAuthMapper.fromDtoSeller(user));
        return user;
    }
    public UserAuthDto getUser(Long id) {
        UserAuth user = userAuthRepo.findById(id).orElse(null);
        return userAuthMapper.toDto(user);
    }
    public List<UserAuth> userList() {
        return (List<UserAuth>)userAuthRepo.findAll();
    }
}
