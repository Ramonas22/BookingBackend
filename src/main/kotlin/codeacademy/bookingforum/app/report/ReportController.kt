package codeacademy.bookingforum.app.report

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(name = "report/")
class ReportController {

    @Autowired
    private lateinit var service: ReportService

    @GetMapping("/")
    fun getAllReports(): List<ReportDto?> {
        return service.getAllReports()
    }

    @GetMapping("/{id}")
    fun getReportById(@PathVariable id: Long): ReportDto? {
        return service.getReportById(id)
    }

    @PostMapping("/")
    fun postReport(@RequestBody report: ReportDto?) {
        service.postReport(report)
    }

    @PutMapping("/{id}")
    fun updateReport(@PathVariable id: Long, @RequestBody report: ReportDto?) {
        service.updateReport(id, report)
    }

    @DeleteMapping("/{id}")
    fun deleteReport(@PathVariable id: Long): String {
        return service.deleteReport(id)
    }
}