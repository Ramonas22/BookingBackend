package codeacademy.bookingforum.app.topicCategory;

import codeacademy.bookingforum.app.configuration.ResponseObject;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/api/section")
public class TopicCategoryController {
    @Autowired
    TopicCategoryService sectionService;

    // Create new section (TopicCategory)
    @Secured("ROLE_ADMIN")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject create(@RequestBody @Valid TopicCategoryDto section, WebRequest request) {
        return sectionService.create(section, request);
    }

    // Get all section id's
    @GetMapping("/list")
    public List<TopicCategoryDto> getList() {
        return sectionService.getList();
    }
}
