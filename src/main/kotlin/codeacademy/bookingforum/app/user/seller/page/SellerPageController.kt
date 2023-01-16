package codeacademy.bookingforum.app.user.seller.page

import codeacademy.bookingforum.app.configuration.ResponseObject
import codeacademy.bookingforum.app.user.auth.dto.UserAuthDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.access.annotation.Secured
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

    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/activate/{username}")
    fun activate(@PathVariable("username") username: String?, request: WebRequest?): ResponseObject? {
        return service.activate(username, request)
    }

    @GetMapping("/getpage/{id}")
    fun getPage(@PathVariable("id") id: Long?): SellerPageDto? {
        return service.getPage(id)
    }

    @GetMapping("/get/sellers")
    fun getSellers(): List<Long> {
        return service.getSellers()
    }

    @Secured("ROLE_SELLER", "ROLE_ADMIN")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/update")
    fun update(@RequestBody page: SellerPageDto, request: WebRequest?): ResponseObject? {
        return service.update(page, request)
    }
}