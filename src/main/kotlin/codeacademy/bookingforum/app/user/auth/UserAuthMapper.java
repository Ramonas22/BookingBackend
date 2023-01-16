package codeacademy.bookingforum.app.user.auth;

import codeacademy.bookingforum.app.purchase.PurchaseDto;
import codeacademy.bookingforum.app.purchase.PurchaseMapper;
import codeacademy.bookingforum.app.enums.Gender;
import codeacademy.bookingforum.app.user.auth.dto.UserAuthDto;
import codeacademy.bookingforum.app.user.auth.dto.UserDetailsDto;
import codeacademy.bookingforum.app.user.auth.dto.UserManagementDto;
import codeacademy.bookingforum.app.user.role.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserAuthMapper {
    @Autowired
    PurchaseMapper purchaseMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepo roleRepo;

    // Used when logging in with existing user.
    public UserAuthDto toDto(UserAuth entity) {
        if (entity == null) {
            return null;
        }
        UserAuthDto dto = new UserAuthDto();
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());

        return dto;
    }

    // Used when registering regular new user.
    public UserAuth fromDtoUser(UserAuthDto dto) {
        if (dto == null) {
            return null;
        }
        UserAuth entity = new UserAuth();

        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setGender(Gender.parse(dto.getGender()));
        entity.setBiography("Hello, my name is "+dto.getUsername()+"!");
        entity.setEnabled(true);
        entity.setJoinDate(LocalDateTime.now());
        if(entity.getRoles() == null) {
            entity.setRoles(new ArrayList<>());
        }
        entity.getRoles().add(roleRepo.findByDisplayName("ROLE_USER"));

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
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setGender(Gender.parse(dto.getGender()));
        entity.setBiography("Hello, my name is "+dto.getUsername()+"!");
        entity.setEnabled(false);
        entity.setJoinDate(LocalDateTime.now());
        if(entity.getRoles() == null) {
            entity.setRoles(new ArrayList<>());
        }
        entity.getRoles().add(roleRepo.findByDisplayName("ROLE_SELLER"));

        return entity;
    }

    // Used for getting all info of a specific user
    public UserManagementDto toDtoManagement(UserAuth entity) {
        if(entity == null) {
            return null;
        }
        List<String> roleDtos = new ArrayList<>();
        List<PurchaseDto> purchaseDtos = new ArrayList<>();
        entity.getRoles().forEach(role -> roleDtos.add(role.getDisplayName()));
        entity.getPurchases().forEach(purchase -> purchaseDtos.add(purchaseMapper.toDto(purchase)));
        return new UserManagementDto(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getGender().toString(),
                entity.getBiography(),
                roleDtos,
                purchaseDtos,
                entity.isEnabled(),
                entity.getJoinDate());
    }

    // Used to load user profile (when that user is the owner)
    public UserDetailsDto toDtoDetails(UserAuth entity) {
        if (entity == null) {
            return null;
        }
        return new UserDetailsDto(entity.getId(), entity.getUsername(), null, entity.getEmail(), entity.getBiography(), entity.getGender().toString());
    }


}
