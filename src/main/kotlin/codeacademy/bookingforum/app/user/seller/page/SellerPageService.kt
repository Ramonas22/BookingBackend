package codeacademy.bookingforum.app.user.seller.page

import codeacademy.bookingforum.app.purchase.Purchase
import codeacademy.bookingforum.app.user.auth.UserAuth
import codeacademy.bookingforum.app.user.seller.rating.SellerRating
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SellerPageService {
    @Autowired
    private lateinit var mapper: SellerPageMapper
    @Autowired
    private lateinit var repo: SellerPageRepo
    fun getSellerPageById(id: Long): SellerPageDto? {
        return repo.findByIdOrNull(id).let { mapper.toDto(it) }
    }

    fun getAllSellersPages(): List<SellerPageDto>? {
        return repo.findAll().map { mapper.toDto(it) }
    }

    fun postSellerPage(dto: SellerPageDto?) {
        mapper.fromDto(dto).let { repo.save(it) }
    }

    fun updateSellerPageById(id: Long, dto: SellerPageDto?): String {
        return if(repo.existsById(id)){
            repo.save(
                SellerPage(
                    id,
                    dto?.description,
                    dto?.galaleryLinks,
                    dto?.priceMin,
                    dto?.priceMax,
                    dto?.unavailableDate,
                    UserAuth(dto?.userId),
                    dto?.sellerRatingsIds?.map { SellerRating(it) },
                    dto?.purchasesIds?.map { Purchase(it) },
                )
            )
            "Seller page with Id $id updated"
        }else{
            "Seller page with $id does not exist"
        }
    }

    fun deleteSellerPageById(id: Long): String {
        return if (repo.existsById(id)){
            repo.deleteById(id)
            "Deleted Seller pagte with $id"
        }else{
            "Seller page does not exist with $id"
        }
    }
}