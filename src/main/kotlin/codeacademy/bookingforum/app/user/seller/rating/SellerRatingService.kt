package codeacademy.bookingforum.app.user.seller.rating

import codeacademy.bookingforum.app.ecxeption.global.UnsatisfiedExpectationException
import codeacademy.bookingforum.app.ecxeption.user.UserNotFoundException
import codeacademy.bookingforum.app.user.auth.UserAuth
import codeacademy.bookingforum.app.user.auth.UserAuthRepo
import codeacademy.bookingforum.app.user.seller.page.SellerPage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.function.Consumer

@Service
class SellerRatingService {
    @Autowired
    private lateinit var mapper: SellerRatingMapper
    @Autowired
    private lateinit var repo: SellerRatingRepo
    @Autowired
    private lateinit var userRepo: UserAuthRepo



    fun getSellerRatings(id: Long): List<Long>? {
        val user: UserAuth = userRepo.findByIdOrNull(id) ?: throw UserNotFoundException("User with id $id does not exist!")

        val ids: ArrayList<Long> = ArrayList()
        user.sellerPage.ratings.forEach(Consumer { rating: SellerRating ->
            ids.add(rating.getId())
        })
        return ids
    }

    fun getAverage(id: Long): Double? {
        val user: UserAuth = userRepo.findByIdOrNull(id) ?: throw UserNotFoundException("User with id $id does not exist!")
        var sum = 0.0
        user.sellerPage.ratings.forEach(Consumer { rating: SellerRating -> sum += rating.rating })

        if (sum == 0.0) {
            throw UnsatisfiedExpectationException("This seller has no ratings yet!")
        }
        return sum / user.sellerPage.ratings.size
    }









    fun getSellerRatingById(id: Long): SellerRatingDto? {
        return repo.findByIdOrNull(id).let { mapper.toDto(it) }
    }

    fun getAllSellersRatings(): List<SellerRatingDto>? {
        return repo.findAll().map { mapper.toDto(it) }
    }

    fun postSellerRating(dto: SellerRatingDto?) {
        repo.save( mapper.fromDto(dto) )
    }

//    fun updateSellerRating(id: Long, dto: SellerRatingDto?): String {
//        return if(repo.existsById(id)){
//            if (dto != null) {
//                repo.save( SellerRating(
//                    id,
//                    dto.userId?.map{ UserAuth(it) },
//                    dto.sellerPageId?.map { SellerPage(it) },
//                    dto.rating,
//                    dto.comment,
//                    dto.date,
//                ))
//            }
//            "User with Id $id updated"
//        }else{
//            "User does not exist"
//        }
//    }

    fun deleteSellerRatingById(id: Long): String {
        return if (repo.existsById(id)){
            repo.deleteById(id)
            "Deleted sellerRating with $id"
        }else{
            "SellerRating does not exist with $id"
        }
    }
}