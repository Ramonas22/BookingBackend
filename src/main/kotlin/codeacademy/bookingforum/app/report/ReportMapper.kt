package codeacademy.bookingforum.app.report

import codeacademy.bookingforum.app.comment.Comment
import codeacademy.bookingforum.app.post.Post
import codeacademy.bookingforum.app.user.auth.UserAuth
import org.springframework.stereotype.Service

@Service
class ReportMapper {

    fun fromDtoToEntity(dto: ReportDto?): Report? {
        return Report(
            id = dto?.id,
            reason = dto?.reason,
            description = dto?.description,
            reportDate = dto?.reportDate,
            commentId = Comment(id = dto?.commentId),
            userID = UserAuth(dto?.userID),
            postId = Post(dto?.postId),
        )
    }

    fun fromEntityToDto(entity: Report?): ReportDto? {
        return ReportDto(
            id = entity?.id,
            reason = entity?.reason,
            description = entity?.description,
            reportDate = entity?.reportDate,
            commentId = entity?.commentId?.id,
            userID = entity?.userID?.id,
            postId = entity?.postId?.id,
        )
    }

    fun fromEntityListToDtoList(entityList: List<Report>?): List<ReportDto?> {
        return if(entityList.isNullOrEmpty()){
            emptyList<ReportDto>()
        }else{
            entityList.map { fromEntityToDto(it) }.toList()
        }
    }
}