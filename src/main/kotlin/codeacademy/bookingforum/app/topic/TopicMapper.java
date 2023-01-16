package codeacademy.bookingforum.app.topic;

import codeacademy.bookingforum.app.topicCategory.TopicCategory;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TopicMapper {

    public TopicDto toDto(Topic entity){
        if(entity == null){
            return null;
        }
        TopicDto dto = new TopicDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setPostCount(entity.getPostCount());
        dto.setReplyCount(entity.getReplyCount());
        dto.setLastPostId(entity.getLastPostId());
        dto.setUserId(entity.getId());
        dto.setSectionId(entity.getSection().getId());

        return dto;
    }

    public Topic fromDto(TopicDto dto){
        if(dto == null){
            return null;
        }

        Topic entity = new Topic();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setPostCount(dto.getPostCount());
        entity.setReplyCount(dto.getReplyCount());
        entity.setLastPostId(dto.getLastPostId());
        entity.setUser(new UserAuth(dto.getUserId()));
        entity.setSection(new TopicCategory(dto.getSectionId()));

        return entity;
    }

    public List<TopicDto> toDtoList(List<Topic> entities){
        List<TopicDto> dtoList = new ArrayList<>();

        entities.forEach(entity -> dtoList.add(toDto(entity)));
        return dtoList;
    }
}
