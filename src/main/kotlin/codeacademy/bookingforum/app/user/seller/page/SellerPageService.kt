package codeacademy.bookingforum.app.user.seller.page

import codeacademy.bookingforum.app.configuration.ResponseObject
import codeacademy.bookingforum.app.ecxeption.global.InvalidRequestException
import codeacademy.bookingforum.app.ecxeption.global.UnsatisfiedExpectationException
import codeacademy.bookingforum.app.ecxeption.sellerPage.PageNotFoundException
import codeacademy.bookingforum.app.ecxeption.user.*
import codeacademy.bookingforum.app.user.auth.UserAuth
import codeacademy.bookingforum.app.user.auth.UserAuthMapper
import codeacademy.bookingforum.app.user.auth.UserAuthRepo
import codeacademy.bookingforum.app.user.auth.UserAuthService
import codeacademy.bookingforum.app.user.auth.dto.UserAuthDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.web.context.request.WebRequest
import java.util.regex.Pattern

@Service
class SellerPageService {
    @Autowired
    private lateinit var pageMapper: SellerPageMapper
    @Autowired
    private lateinit var pageRepo: SellerPageRepo
    @Autowired
    private lateinit var userAuthRepo: UserAuthRepo
    @Autowired
    private lateinit var userAuthMapper: UserAuthMapper

    // Used to register a user account with ROLE_SELLER, initial activation is false, admin approval required
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

    // Used to activate a user account with ROLE_SELLER, additionally creates a SellerPage for the given user.
    fun activate(username: String?, request: WebRequest?): ResponseObject? {
        val user = userAuthRepo.findByUsername(username)
        if (user == null) {
            throw UserNotFoundException("User with username $username does not exist.")
        } else if (user.isEnabled) {
            throw AccountAlreadyActivatedException("Account $username is already activated.")
        }
        val roles = user.roles
        var isSeller = false
        for (role in roles) {
            if (role.displayName == "ROLE_SELLER") {
                isSeller = true
                break
            }
        }
        if (!isSeller) {
            throw UnsatisfiedExpectationException("Cannot create pages for non-sellers!")
        }


        val sellerPage = SellerPage(user)
        val page = pageRepo.save(sellerPage)

        user.isEnabled = true
        user.sellerPage = page
        userAuthRepo.save(user)

        return ResponseObject(
            listOf("Page and activation for seller $username was performed successfully."),
            HttpStatus.CREATED,
            request
        )
    }

    // Used to get a seller's page properties
    fun getPage(id: Long?): SellerPageDto? {
        val user = userAuthRepo.findByIdOrNull(id)
        if (user == null) {
            throw UserNotFoundException("User with id $id does not exist.")
        } else if (user.sellerPage == null) {
            throw PageNotFoundException("Page for this user was not found. Possible reasons: not a seller, non-activated account.")
        }
        return pageMapper.toDtoList(user.sellerPage)
    }

    fun getSellers(): List<Long> {
        return userAuthRepo.activeSellerIds
    }

    fun update(page: SellerPageDto, request: WebRequest?): ResponseObject? {
        val userDetails = SecurityContextHolder.getContext().authentication.principal as UserDetails
        val user = userAuthRepo.findByUsername(userDetails.username)
        if (user == null) {
            throw UserNotFoundException("User with username " + userDetails.username + " does not exist.")
        } else if (user.id != page.userId) {
            throw UnsatisfiedExpectationException("Provided user and authenticated user do not match!")
        }
        val original = pageRepo.findByIdOrNull(page.userId)

        pageRepo.save(pageMapper.update(original, page))

        return ResponseObject(
            listOf("Page updated successfully."),
            HttpStatus.CREATED,
            request
        )
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