package codeacademy.bookingforum.app.topic;

import codeacademy.bookingforum.app.topicCategory.TopicCategory;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import codeacademy.bookingforum.app.user.seller.page.SellerPage;
import codeacademy.bookingforum.app.user.seller.page.SellerPageDto;

import java.util.ArrayList;
import java.util.List;

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
        dto.setReplayCount(entity.getReplayCount());
        dto.setPostLast(entity.getPostLast());
        dto.setUserLastPost(entity.getUserLastPost());
        dto.setUser_id(entity.getId());
        dto.setTopicCategory_id(entity.getId());

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
        entity.setReplayCount(dto.getReplayCount());
        entity.setPostLast(dto.getPostLast());
        entity.setUserLastPost(dto.getUserLastPost());
        entity.setUser(new UserAuth(dto.getUser_id()));
        entity.setTopicCategory(new TopicCategory(dto.getTopicCategory_id()));

        return entity;
    }

    public List<TopicDto> toDto(List<Topic> ent){
        List<TopicDto> dtos = new ArrayList<>();

        for(Topic entity : ent){
            dtos.add((TopicDto) toDto(entity));
        }
        return dtos;
    }
}
