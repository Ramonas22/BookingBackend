package codeacademy.bookingforum.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BookingAppApplication

fun main(args: Array<String>) {
	runApplication<BookingAppApplication>(*args)
}
