package codeacademy.bookingforum.app.user.seller.page

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
@RequestMapping("/sellerPage")
class SellerPageController {
    @Autowired
    private lateinit var service: SellerPageService

    @GetMapping("/getSellerPageById/{id}")
    fun getSellerPageById(@PathVariable id: Long): SellerPageDto? {
        return service.getSellerPageById(id)
    }

    @GetMapping("/getAllSellersPages")
    fun getAllSellersPages(): List<SellerPageDto>? {
        return service.getAllSellersPages()
    }

    @PostMapping("/postSellerPage")
    fun postSellerPage(@RequestBody dto: SellerPageDto?){
        service.postSellerPage(dto)
    }

    @PutMapping("/updateSellerPage/{id}")
    fun updateSellerPageById(@RequestBody dto: SellerPageDto?,@PathVariable id: Long): String {
        return service.updateSellerPageById(id, dto)
    }

    @DeleteMapping("/deleteSellerPageById/{id}")
    fun deleteSellerPageById(@PathVariable id: Long): String {
        return service.deleteSellerPageById(id)
    }

}