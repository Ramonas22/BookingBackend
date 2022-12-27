package codeacademy.bookingforum.app.purchase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDto {

    private Long id;

    private String details;

    private LocalDateTime bookedDate;

    private List<Long> orderUser_id;

    private List<Long> orderSeller_id;

}
