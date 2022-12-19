package codeacademy.bookingforum.app.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;

    private String details;

    private Date bookedDate;

    private List<Long> orderUser_id;

    private List<Long> orderSeller_id;

}
