package codeacademy.bookingforum.app.user.seller.rating

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/rating")
class SellerRatingController {
    @Autowired
    private lateinit var service: SellerRatingService

    @GetMapping("/get/{id}") // ID of a page
    fun getSellerRatings(@PathVariable("id") id: Long): List<Long>? {
        return service.getSellerRatings(id)
    }

    @GetMapping("/average/{id}") // ID of a page
    fun getAverage(@PathVariable("id") id: Long): Double? {
        return service.getAverage(id)
    }








    @GetMapping("/getSellerRating/{id}")
    fun getSellerRatingById(@PathVariable id: Long): SellerRatingDto? {
        return service.getSellerRatingById(id)
    }

    @GetMapping("/getAllSellersRatings")
    fun getAllSellersRatings(): List<SellerRatingDto>? {
        return service.getAllSellersRatings()
    }

    @PostMapping("/postSellerRating")
    fun postSellerRating(@RequestBody dto: SellerRatingDto?) {
        service.postSellerRating(dto)
    }

//    @PutMapping("/updateSellerRating/{id}")
//    fun putSellerRating(@RequestBody dto: SellerRatingDto?, @PathVariable id: Long): String {
//        return service.updateSellerRating(id, dto)
//    }

    @DeleteMapping("/delete/{id}")
    fun deleteSellerRatingById(@PathVariable id: Long): String {
        return service.deleteSellerRatingById(id)
    }
}