package codeacademy.bookingforum.app.user.seller.page

import codeacademy.bookingforum.app.configuration.ResponseObject
import codeacademy.bookingforum.app.user.auth.dto.UserAuthDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.WebRequest
import javax.validation.Valid

@RestController
@RequestMapping("/api/seller")
class SellerPageController {
    @Autowired
    private lateinit var service: SellerPageService

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@RequestBody @Valid user: UserAuthDto?, request: WebRequest?): ResponseObject? {
        return service.register(user, request)
    }

    @GetMapping("/get/all")
    fun getAll(): List<Long> {
        return service.getAll()
    }

//    @GetMapping("/get/preview")
//    fun getPreview(): Map<Long, String> {
//        return service.getPreview();
//    }






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