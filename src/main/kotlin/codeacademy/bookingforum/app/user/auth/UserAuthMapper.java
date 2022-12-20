package codeacademy.bookingforum.app.user.auth;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class UserAuthMapper {

    // Used when logging in with existing user.
    public UserAuthDto toDto(UserAuth entity) {
        if (entity == null) {
            return null;
        }
        UserAuthDto dto = new UserAuthDto();
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());

        return dto;
    }

    // Used when registering new user.
    public UserAuth fromDto(UserAuthDto dto) {
        if (dto == null) {
            return null;
        }
        UserAuth entity = new UserAuth();

        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setGender(dto.getGender());
        entity.setBiography("Hello, my name is "+dto.getUsername()+"!");
        entity.setEnabled(true);
        entity.setJoinDate(LocalDateTime.now());

        return entity;
    }

    // Used when registering new user as Seller.
    public UserAuth fromDtoSeller(UserAuthDto dto) {
        if (dto == null) {
            return null;
        }
        UserAuth entity = new UserAuth();

        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setGender(dto.getGender());
        entity.setBiography("Hello, my name is "+dto.getUsername()+"!");
        entity.setEnabled(false);
        entity.setJoinDate(LocalDateTime.now());

        return entity;
    }
}
