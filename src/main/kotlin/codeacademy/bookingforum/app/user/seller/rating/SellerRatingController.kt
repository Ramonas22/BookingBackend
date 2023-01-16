package codeacademy.bookingforum.app.user.seller.rating

import codeacademy.bookingforum.app.configuration.ResponseObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.WebRequest
import javax.validation.Valid

@RestController
@RequestMapping("/api/rating")
class SellerRatingController {
    @Autowired
    private lateinit var service: SellerRatingService

    // Get a list of rating id's that belong to current page
    @GetMapping("/all/{id}") // ID of a page
    fun getSellerRatings(@PathVariable("id") id: Long): List<Long>? {
        return service.getSellerRatings(id)
    }

    // Get average rating of a page
    @GetMapping("/average/{id}") // ID of a page
    fun getAverage(@PathVariable("id") id: Long): Double? {
        return service.getAverage(id)
    }

    // Create new rating
    @Secured("ROLE_USER", "ROLE_ADMIN")
    @PostMapping("/new")
    fun newRating(request: WebRequest?, @RequestBody @Valid sellerRatingDto: SellerRatingDto?): ResponseObject? {
        return service.newRating(request, sellerRatingDto)
    }

    // Get on rating by id
    @GetMapping("/get/{id}") // ID of a rating
    fun getById(@PathVariable("id") id: Long):SellerRatingDto? {
        return service.getById(id)
    }
}