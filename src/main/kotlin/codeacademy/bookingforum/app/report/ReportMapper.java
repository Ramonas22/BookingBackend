package codeacademy.bookingforum.app.report;

import codeacademy.bookingforum.app.comment.Comment;
import codeacademy.bookingforum.app.post.Post;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportMapper {
    public ReportDto toDto(Report entity) {
        if (entity == null) {
            return null;
        }
        ReportDto dto = new ReportDto();

        dto.setId(entity.getId());
        dto.setReason(entity.getReason());
        dto.setDescription(entity.getDescription());
        dto.setReportDate(entity.getReportDate());
        dto.setUserId(entity.getUser().getId());
        dto.setPostId(entity.getPost().getId());
        dto.setCommentId(entity.getComment().getId());

        return dto;
    }

    public Report fromDto(ReportDto dto) {
        if (dto == null) {
            return null;
        }
        Report entity = new Report();

        entity.setId(dto.getId());
        entity.setReason(dto.getReason());
        entity.setDescription(dto.getDescription());
        entity.setReportDate(LocalDateTime.now());
        if (dto.getUserId() != null) {
            entity.setUser(new UserAuth(dto.getUserId()));
        }
        if (dto.getPostId() != null) {
            entity.setPost(new Post(dto.getPostId()));
        }
        if (dto.getCommentId() != null) {
            entity.setComment(new Comment(dto.getCommentId()));
        }

        return entity;
    }

    public List<ReportDto> toDtoList(List<Report> entities) {
        if (entities == null) {
            return null;
        }
        List<ReportDto> dtoList = new ArrayList<>();
        entities.forEach(entity -> dtoList.add(toDto(entity)));
        return dtoList;
    }
}
