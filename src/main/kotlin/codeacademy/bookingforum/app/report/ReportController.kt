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
@RequestMapping("/report")
class ReportController {

    @Autowired
    private lateinit var service: ReportService

    @GetMapping("/getAllReports")
    fun getAllReports(): List<ReportDto?> {
        return service.getAllReports()
    }

    @GetMapping("/getReport/{id}")
    fun getReportById(@PathVariable id: Long): ReportDto? {
        return service.getReportById(id)
    }

    @PostMapping("/postReport")
    fun postReport(@RequestBody report: ReportDto?) {
        service.postReport(report)
    }

    @PutMapping("/updateReport/{id}")
    fun updateReport(@PathVariable id: Long, @RequestBody report: ReportDto?): String {
        return service.updateReport(id, report)
    }

    @DeleteMapping("/deleteReport/{id}")
    fun deleteReport(@PathVariable id: Long): String {
        return service.deleteReport(id)
    }
}