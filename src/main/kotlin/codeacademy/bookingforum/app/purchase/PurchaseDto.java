package codeacademy.bookingforum.app.purchase;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDto {
    private Long id;
    private String details;
    private Date bookedDate;
    private Long userId;
    private Long pageId;

}
