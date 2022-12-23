package codeacademy.bookingforum.app.order;

import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepo extends CrudRepository<Purchase, Long> {
}
