package codeacademy.bookingforum.app.topic;

import codeacademy.bookingforum.app.configuration.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/api/topic")
public class TopicController {
    @Autowired
    TopicService topicService;

    @Secured("ROLE_ADMIN")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject create(@RequestBody TopicDto topic, WebRequest request) {
        return topicService.create(topic, request);
    }

    @GetMapping("/list/{id}") // ID of section (TopicCategory)
    public List<TopicDto> getList(@PathVariable("id") Long id) {
        return topicService.getList(id);
    }
}
