package codeacademy.bookingforum.app.topicCategory;

import org.springframework.stereotype.Service;

@Service
public class TopicCategoryMapper {
    public TopicCategoryDto toDto(TopicCategory entity) {
        if (entity == null) {
            return null;
        }
        TopicCategoryDto dto = new TopicCategoryDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setRoles(entity.getRoles());
        return dto;
    }

    public TopicCategory fromDto(TopicCategoryDto dto) {
        if (dto == null) {
            return null;
        }
        TopicCategory entity = new TopicCategory();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setRoles(dto.getRoles());
        return entity;
    }
}
