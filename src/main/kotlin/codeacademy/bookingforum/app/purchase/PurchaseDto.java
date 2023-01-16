package codeacademy.bookingforum.app.purchase;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDto {
    private Long id;
    private String details;
    @NotEmpty(message = "No date provided!")
    private LocalDateTime bookedDate;
    @NotEmpty(message = "No customer provided!")
    private Long userId;
    @NotEmpty(message = "No seller page provided!")
    private Long pageId;

}
