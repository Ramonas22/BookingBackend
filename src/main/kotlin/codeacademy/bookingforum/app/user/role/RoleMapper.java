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
//    public List<RoleDto> toDtoList(List<Role> entities) {
//        List<RoleDto> dtos = new ArrayList<>();
//        entities.forEach(entity -> {dtos.add(this.toDto(entity));});
//        return dtos;
//    }
//    public List<Role> toEntityList(List<RoleDto> dtos) {
//        List<Role> entities = new ArrayList<>();
//        dtos.forEach(dto -> {entities.add(this.fromDto(dto));});
//        return entities;
//    }
}
