package codeacademy.bookingforum.app.topic;

import codeacademy.bookingforum.app.configuration.ResponseObject;
import codeacademy.bookingforum.app.ecxeption.global.UnsatisfiedExpectationException;
import codeacademy.bookingforum.app.topicCategory.TopicCategory;
import codeacademy.bookingforum.app.topicCategory.TopicCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;
import java.util.List;

@Service
public class TopicService {
    @Autowired
    TopicRepository topicRepo;
    @Autowired
    TopicMapper topicMapper;
    @Autowired
    TopicCategoryRepo sectionRepo;

    public ResponseObject create(TopicDto topic, WebRequest request) {
        TopicCategory section = sectionRepo.findById(topic.getSectionId()).orElse(null);
        if (section == null) {
            throw new UnsatisfiedExpectationException("Can't save topic to non-existing section!");
        }
        Topic oldTopic = topicRepo.findByTitle(topic.getTitle().trim());
        if (oldTopic != null) {
            throw new UnsatisfiedExpectationException("This topic already exists!");
        }

        section.getTopics().add(topicRepo.save(topicMapper.fromDto(topic)));

        return new ResponseObject(Collections.singletonList("Successfully added new topic."), HttpStatus.CREATED, request);
    }

    public List<TopicDto> getList(Long id) {
        TopicCategory section = sectionRepo.findById(id).orElse(null);
        if (section == null) {
            throw new UnsatisfiedExpectationException("Section with id "+id+" does not exist!");
        }

        List<Topic> topics = topicRepo.findAllBySection(section);
        return topicMapper.toDtoList(topics);
    }
}
