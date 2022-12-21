package codeacademy.bookingforum.app.topicCategory

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
@RequestMapping("/topicCategory")
class TopicCategoryController {

    @Autowired
    private lateinit var service: TopicCategoryService
    @GetMapping("/")
    fun getTopicCategories(): List<TopicCategoryDto?> {
        return service.getAllTopicCategories()
    }

    @GetMapping("/{id}")
    fun getTopicCategoryById(@PathVariable id: Long): TopicCategoryDto? {
        return service.getAllTopicCategoryById(id)
    }

    @PostMapping("/")
    fun postTopicCategory(@RequestBody topicCategoryDto: TopicCategoryDto?) {
        service.postTopicCategory(topicCategoryDto)
    }

    @PutMapping("/{id}")
    fun updateTopicCategory(@PathVariable id: Long, @RequestBody topicCategoryDto: TopicCategoryDto?){
        service.updateTopicCategory(id, topicCategoryDto)
    }

    @DeleteMapping("/{id}")
    fun deleteTopicCategory(@PathVariable id: Long): String{
        return service.deleteTopicCategory(id)
    }
}