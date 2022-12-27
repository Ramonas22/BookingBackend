package codeacademy.bookingforum.app.purchase;

import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepo extends CrudRepository<Purchase, Long> {
}
