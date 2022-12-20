package codeacademy.bookingforum.app.report

import org.springframework.stereotype.Service

@Service
class ReportMapper {

    fun fromDtoToEntity(dto: ReportDto?): Report? {
        return Report(
            id = dto?.id,
            reason = dto?.reason,
            description = dto?.description,
            reportDate = dto?.reportDate,
            commentId = dto?.commentId,
            userID = dto?.userID,
            postId = dto?.postId,
        )
    }

    fun fromEntityToDto(entity: Report?): ReportDto? {
        return ReportDto(
            id = entity?.id,
            reason = entity?.reason,
            description = entity?.description,
            reportDate = entity?.reportDate,
            commentId = entity?.commentId,
            userID = entity?.userID,
            postId = entity?.postId,
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