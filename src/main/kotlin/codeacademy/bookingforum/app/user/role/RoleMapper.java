package codeacademy.bookingforum.app.user.role;

import org.springframework.stereotype.Service;

@Service
public class RoleMapper {
    public RoleDto toDto(Role entity) {
        RoleDto dto = new RoleDto();
        dto.setId(entity.getId());
        dto.setDisplayName(entity.getDisplayName());
        return dto;
    }
    public Role fromDto(RoleDto dto) {
        Role entity = new Role();
        entity.setId(dto.getId());
        entity.setDisplayName(dto.getDisplayName());
        return entity;
    }
}
