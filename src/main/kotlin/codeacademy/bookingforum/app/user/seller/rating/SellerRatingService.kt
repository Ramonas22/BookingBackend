package codeacademy.bookingforum.app.user.seller.rating

import codeacademy.bookingforum.app.user.auth.UserAuth
import codeacademy.bookingforum.app.user.seller.page.SellerPage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SellerRatingService {
    @Autowired
    private lateinit var mapper: SellerRatingMapper
    @Autowired
    private lateinit var repo: SellerRatingRepo
    fun getSellerRatingById(id: Long): SellerRatingDto? {
        return repo.findByIdOrNull(id).let { mapper.toDto(it) }
    }

    fun getAllSellersRatings(): List<SellerRatingDto>? {
        return repo.findAll().map { mapper.toDto(it) }
    }

    fun postSellerRating(dto: SellerRatingDto?) {
        repo.save( mapper.fromDto(dto) )
    }

    fun updateSellerRating(id: Long, dto: SellerRatingDto?): String {
        return if(repo.existsById(id)){
            repo.save( SellerRating(
                id,
                dto?.userIds?.map{ UserAuth(it) },
                dto?.sellerRatingIds?.map { SellerPage(it) },
                dto?.rating,
                dto?.comment,
                dto?.date,
            ))
            "User with Id $id updated"
        }else{
            "User does not exist"
        }
    }

    fun deleteSellerRatingById(id: Long): String {
        return if (repo.existsById(id)){
            repo.deleteById(id)
            "Deleted user with $id"
        }else{
            "User does not exist with $id"
        }
    }
}