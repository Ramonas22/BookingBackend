package codeacademy.bookingforum.app.report;

import codeacademy.bookingforum.app.configuration.ResponseObject;
import codeacademy.bookingforum.app.ecxeption.global.UnsatisfiedExpectationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    ReportMapper reportMapper;
    @Autowired
    ReportRepository reportRepo;

    public ResponseObject report(ReportDto reportDto, WebRequest request) {
        if (validateReport(reportDto)) {
            reportRepo.save(reportMapper.fromDto(reportDto));
        }
        return new ResponseObject(Collections.singletonList("Report sent successfully."), HttpStatus.CREATED, request);
    }

    public List<ReportDto> getAll() {
        List<Report> reports = reportRepo.findAll();
        return reportMapper.toDtoList(reports);
    }

    public ResponseObject delete(Long id, WebRequest request) {
        if (reportRepo.existsById(id)) {
            reportRepo.deleteById(id);
        }
        return new ResponseObject(Collections.singletonList("Report successfully deleted!"), HttpStatus.OK, request);
    }




    // To pass validation, only ONE of id fields must be provided for "userId, postId, commentId".
    @SuppressWarnings("ConstantConditions")
    boolean validateReport(ReportDto reportDto) {
        Long[] nulls = {null, null, null};
        nulls[0] = reportDto.getUserId();
        nulls[1] = reportDto.getPostId();
        nulls[2] = reportDto.getCommentId();

        if (nulls[0] == null && nulls[1] == null && nulls[2] == null) {
            throw new UnsatisfiedExpectationException("You have not provided who you are reporting.");
        }

        // This warning is INVALID: 'nulls[n] != null' is always 'true'
        if (nulls[0] == null && nulls[1] == null && nulls[2] != null) {
            return true;
        } else if (nulls[0] == null && nulls[1] != null && nulls[2] == null) {
            return true;
        } else if (nulls[0] != null && nulls[1] == null && nulls[2] == null) {
            return true;
        } else {
            throw new UnsatisfiedExpectationException("You can't report multiple things at once!");
        }


    }
}
