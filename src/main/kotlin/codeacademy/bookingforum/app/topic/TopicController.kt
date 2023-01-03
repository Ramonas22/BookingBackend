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
@RequestMapping(name = "/topic")
class TopicController {

    @Autowired
    private lateinit var service: TopicService

    @GetMapping("/getAllTopics")
    fun getAllTopics(): List<TopicDto?> {
        return service.getAllTopics()
    }

    @GetMapping("/getTopicById/{id}")
    fun getTopicById(@PathVariable id: Long): TopicDto? {
        return service.getTopicById(id)
    }

    @PostMapping("/postTopic")
    fun postTopic(@RequestBody topicDto: TopicDto?) {
        service.postTopic(topicDto)
    }

    @PutMapping("/updateTopicById/{id}")
    fun updateTopic(@PathVariable id: Long, @RequestBody topicDto: TopicDto?): String {
        return service.updateTopic(id, topicDto)
    }

    @DeleteMapping("/{id}")
    fun deleteTopic(@PathVariable id: Long): String {
        return service.deleteTopic(id)
    }
}