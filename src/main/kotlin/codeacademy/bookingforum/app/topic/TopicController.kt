package codeacademy.bookingforum.app.topic

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
@RequestMapping(name = "/topicController/")
class TopicController {

    @Autowired
    private lateinit var service: TopicService

    @GetMapping("/")
    fun getAllTopics(): List<TopicDto?> {
        return service.getAllTopics()
    }

    @GetMapping("/{id}")
    fun getTopicById(@PathVariable id: Long): TopicDto? {
        return service.getTopicById(id)
    }

    @PostMapping("/")
    fun postTopic(@RequestBody topicDto: TopicDto?) {
        service.postTopic(topicDto)
    }

    @PutMapping("/{id}")
    fun updateTopic(@PathVariable id: Long, @RequestBody topicDto: TopicDto?) {
        service.updateTopic(id, topicDto)
    }

    @DeleteMapping("/{id}")
    fun deleteTopic(@PathVariable id: Long): String {
        return service.deleteTopic(id)
    }
}