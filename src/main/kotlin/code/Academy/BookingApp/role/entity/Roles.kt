package code.Academy.BookingApp.role.entity

import lombok.Data
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Roles(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id :Long,
    val roles: String?
)
