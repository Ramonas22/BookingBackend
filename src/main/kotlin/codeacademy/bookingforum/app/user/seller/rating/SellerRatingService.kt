package codeacademy.bookingforum.app.user.seller.rating

import codeacademy.bookingforum.app.configuration.ResponseObject
import codeacademy.bookingforum.app.ecxeption.global.InvalidRequestException
import codeacademy.bookingforum.app.ecxeption.global.UnsatisfiedExpectationException
import codeacademy.bookingforum.app.ecxeption.sellerPage.PageNotFoundException
import codeacademy.bookingforum.app.ecxeption.sellerPage.RatingNotFoundException
import codeacademy.bookingforum.app.ecxeption.user.UserNotFoundException
import codeacademy.bookingforum.app.user.auth.UserAuth
import codeacademy.bookingforum.app.user.auth.UserAuthRepo
import codeacademy.bookingforum.app.user.role.Role
import codeacademy.bookingforum.app.user.seller.page.SellerPageRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.web.context.request.WebRequest
import java.util.function.Consumer

@Service
class SellerRatingService {
    @Autowired
    private lateinit var mapper: SellerRatingMapper
    @Autowired
    private lateinit var ratingRepo: SellerRatingRepo
    @Autowired
    private lateinit var userRepo: UserAuthRepo
    @Autowired
    private lateinit var pageRepo: SellerPageRepo

    // Get a list of rating id's that belong to current page
    fun getSellerRatings(id: Long): List<Long>? {
        val user: UserAuth = userRepo.findByIdOrNull(id) ?: throw UserNotFoundException("User with id $id does not exist!")

        val userRoles: MutableList<String> = java.util.ArrayList()
        user.roles.forEach(Consumer { role: Role ->
            userRoles.add(
                role.displayName
            )
        })
        if (!userRoles.contains("ROLE_SELLER")) {
            throw UnsatisfiedExpectationException("Provided user is not a seller and does not have a page!")
        }

        val ids: ArrayList<Long> = ArrayList()
        user.sellerPage.ratings.forEach(Consumer { rating: SellerRating ->
            ids.add(rating.getId())
        })
        return ids
    }

    // Get the average rating of a page
    fun getAverage(id: Long): Double? {
        val user: UserAuth = userRepo.findByIdOrNull(id) ?: throw UserNotFoundException("User with id $id does not exist!")

        val userRoles: MutableList<String> = java.util.ArrayList()
        user.roles.forEach(Consumer { role: Role ->
            userRoles.add(
                role.displayName
            )
        })
        if (!userRoles.contains("ROLE_SELLER")) {
            throw UnsatisfiedExpectationException("Provided user is not a seller and does not have a page!")
        }

        var sum = 0.0
        user.sellerPage.ratings.forEach(Consumer { rating: SellerRating -> sum += rating.rating })

        if (sum == 0.0) {
            throw UnsatisfiedExpectationException("This seller has no ratings yet!")
        }
        return sum / user.sellerPage.ratings.size
    }

    // Should add a check to see if voting user has had any purchases from current page
    fun newRating(request: WebRequest?, sellerRatingDto: SellerRatingDto?): ResponseObject {
        val userDetails = SecurityContextHolder.getContext().authentication.principal as UserDetails
        val userAuth = userRepo.findByIdOrNull(sellerRatingDto?.userId)
        val sellerPage = pageRepo.findByIdOrNull(sellerRatingDto?.sellerPageId)

        if (sellerRatingDto == null) {
            throw UnsatisfiedExpectationException("Empty request body. Check your code, damn!")
        }
        if (sellerRatingDto.rating > 5) {
            throw UnsatisfiedExpectationException("Rating higher than 5 is not allowed!")
        }
        if (userAuth == null) {
            throw UserNotFoundException("User with id "+ sellerRatingDto.userId +" does not exist!")
        } else if (userDetails.username != userAuth.username) {
            throw InvalidRequestException("You can't vote in someone else's name!")
        } else if (sellerPage == null) {
            throw PageNotFoundException("Seems like this page doesn't exist. Please reload the page and try again.")
        } else if (ratingRepo.findByUserIdAndPageId(userAuth.id, sellerPage.id) != null) {
            throw InvalidRequestException("You have already left a review for this page!")
        }

        ratingRepo.save(mapper.fromDto(sellerRatingDto))
        return ResponseObject(
            listOf("Your review was saved."),
            HttpStatus.CREATED,
            request
        )
    }

    // Get a rating by id
    fun getById(id: Long): SellerRatingDto {
        val sellerRatingDto = ratingRepo.findByIdOrNull(id)
        if (sellerRatingDto == null) {
            throw RatingNotFoundException("Rating with id $id does not exist!")
        } else {
            return mapper.toDto(sellerRatingDto)
        }
    }
}