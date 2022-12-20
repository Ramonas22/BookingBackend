package codeacademy.bookingforum.app.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;


    @PostMapping
    public OrderDto addOrder(@RequestBody OrderDto orderDto){
        return orderService.createOrder(orderDto);
    }

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable(name = "id") Long id){
        return orderService.findById(id);
    }

    @GetMapping
    public List<OrderDto> findAllOrders() {return orderService.findAllOrder();}

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable(name = "id") Long id) {
        orderService.deleteOrder(id);
    }

    @PutMapping("/{id}")
    public OrderDto updateOrder(@RequestBody OrderDto orderdto, @PathVariable(name = "id") Long id){
        return orderService.updateOrder(orderdto);
    }

}
