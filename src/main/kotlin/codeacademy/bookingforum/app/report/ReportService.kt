package codeacademy.bookingforum.app.report

import codeacademy.bookingforum.app.comment.CommentDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ReportService {
    @Autowired
    private lateinit var mapper: ReportMapper
    @Autowired
    private lateinit var repository: ReportRepository
    fun getAllReports(): List<ReportDto?> {
        return repository.findAll().map { mapper.fromEntityToDto(it) }
    }

    fun getReportById(id: Long): ReportDto? {
        return repository.findByIdOrNull(id).let { mapper.fromEntityToDto(it) }
    }

    fun postReport(report: ReportDto?) {
        mapper.fromDtoToEntity(report)?.let { repository.save(it) }
    }

    fun updateReport(id: Long, report: ReportDto?) {
        mapper.fromDtoToEntity(
            ReportDto(
                id = id,
                reason = report?.reason,
                description = report?.description,
                reportDate = report?.reportDate,
                commentId = report?.commentId,
                userID = report?.userID,
                postId = report?.postId,
            )
        )?.let { repository.save(it) }
    }

    fun deleteReport(id: Long): String {
        return if(repository.existsById(id)){
            repository.deleteById(id)
            "Deleted user with id $id"
        }else{
            "Id not found"
        }
    }
}