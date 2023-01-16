package codeacademy.bookingforum.app.report;

import codeacademy.bookingforum.app.configuration.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    @Autowired
    ReportService reportService;

    @Secured({"ROLE_ADMIN","ROLE_USER","ROLE_SELLER"})
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject report(@RequestBody @Valid ReportDto reportDto, WebRequest request) {
        return reportService.report(reportDto, request);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/all")
    public List<ReportDto> getAll() {
        return reportService.getAll();
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/delete/{id}") // ID of report
    public ResponseObject delete(@PathVariable("id") Long id, WebRequest request) {
        return reportService.delete(id, request);
    }

}
