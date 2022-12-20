package codeacademy.bookingforum.app.report

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ReportRepository : CrudRepository<Report, Long> {
}