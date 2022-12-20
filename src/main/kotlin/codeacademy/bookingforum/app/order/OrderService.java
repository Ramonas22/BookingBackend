package codeacademy.bookingforum.app.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderService {

    @Autowired
    OrderRepo orderrepo;

    @Autowired
    OrderMapper ordermapper;


    public OrderDto findById(Long id){

        Order order = orderrepo.findById(id).orElse(null);
        return ordermapper.toDto(order);
    }

    public List<OrderDto> findAllOrder(){
        List<Order> orders = (List<Order>) orderrepo.findAll();

        return ordermapper.toDto(orders);
    }

    public OrderDto createOrder(OrderDto orderDto){
        Order order = ordermapper.fromDto(orderDto);
        orderrepo.save(order);
        return ordermapper.toDto(order);
    }

    public OrderDto updateOrder(OrderDto orderDto){
        Order order = ordermapper.fromDto(orderDto);
        orderrepo.save(order);
        return ordermapper.toDto(order);
    }

    public void deleteOrder(Long id){
        orderrepo.deleteById(id);
    }







}
