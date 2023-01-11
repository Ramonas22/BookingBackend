package codeacademy.bookingforum.app.user.seller.page

import codeacademy.bookingforum.app.configuration.ResponseObject
import codeacademy.bookingforum.app.ecxeption.global.InvalidRequestException
import codeacademy.bookingforum.app.ecxeption.user.*
import codeacademy.bookingforum.app.purchase.Purchase
import codeacademy.bookingforum.app.user.auth.UserAuth
import codeacademy.bookingforum.app.user.auth.UserAuthMapper
import codeacademy.bookingforum.app.user.auth.UserAuthRepo
import codeacademy.bookingforum.app.user.auth.UserAuthService
import codeacademy.bookingforum.app.user.auth.dto.UserAuthDto
import codeacademy.bookingforum.app.user.seller.rating.SellerRating
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.context.request.WebRequest
import java.util.regex.Pattern

@Service
class SellerPageService {
    @Autowired
    private lateinit var mapper: SellerPageMapper
    @Autowired
    private lateinit var repo: SellerPageRepo
    @Autowired
    private lateinit var userAuthRepo: UserAuthRepo
    @Autowired
    private lateinit var userAuthMapper: UserAuthMapper

    fun register(user: UserAuthDto?, request: WebRequest?): ResponseObject? {
        return if (validateUser(user)) {
            val newSeller: UserAuth = userAuthRepo.save(userAuthMapper.fromDtoSeller(user))
            val response = ResponseObject(ArrayList(), HttpStatus.CREATED, request)
            response.messages.add("User " + newSeller.username + " was created successfully.")
            response.messages.add("An admin will review your request within 24 hours. We will send you an email with updates.")
            response
        } else {
            throw InvalidRequestException("Invalid request. Something went wrong.")
        }
    }

    fun getAll(): List<Long> {
        return userAuthRepo.sellerIds
    }

//    fun getPreview(): Map<Long, String> {
//        val sellers: List<Long> = userAuthRepo.sellerIds
//        sellers.forEach()
//
//    }













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
                    dto?.galleryLinks,
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
            "Deleted Seller page with $id"
        }else{
            "Seller page does not exist with $id"
        }
    }






    // ### Validations and checks ###

    // ### Validations and checks ###
    // Checking if DTO is null, if username or email are taken, if password and repeatPassword match, if username and password formats comply with regex
    // Http Status Codes are set individually for every type of exception in UserExceptionHandler.class
    private fun validateUser(user: UserAuthDto?): Boolean {
        return if (user == null) {
            throw UserNotFoundException("Invalid request. No user defined.")
        } else if (userAuthRepo.findByUsername(user.username) != null) {
            throw UserAlreadyExistsException("Username is taken.")
        } else if (userAuthRepo.findByEmail(user.email) != null) {
            throw UserAlreadyExistsException("User with this email already exists.")
        } else if (user.password != user.repeatPassword) {
            throw PasswordMismatchException("Passwords don't match.")
        } else if (validateRegex(user.username, UserAuthService.USERNAME_PATTERN)) {
            throw UsernameFormatException("Username does not match regex.") // More messages are added in UserExceptionHandler.class #usernameFormatHandler
        } else if (validateRegex(user.password, UserAuthService.PASSWORD_PATTERN)) {
            throw PasswordFormatException("Password does not match regex.") // More messages are added in UserExceptionHandler.class #passwordFormatHandler
        } else {
            true
        }
    }

    // Method to validate if a given String matches a given regex pattern.
    private fun validateRegex(str: String, pattern: Pattern): Boolean {
        val matcher = pattern.matcher(str)
        return !matcher.matches()
    }
}