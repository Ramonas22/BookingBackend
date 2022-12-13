package code.Academy.BookingApp.role.repository

import code.Academy.BookingApp.role.entity.Roles
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RolesRepository : CrudRepository<Roles, Long> {
}