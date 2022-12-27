package codeacademy.bookingforum.app.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;


    @PostMapping
    public PurchaseDto addOrder(@RequestBody PurchaseDto purchaseDto){
        return purchaseService.createOrder(purchaseDto);
    }

    @GetMapping("/gelOrder/{id}")
    public PurchaseDto getOrder(@PathVariable(name = "id") Long id){
        return purchaseService.findById(id);
    }

    @GetMapping("/getAllOrders")
    public List<PurchaseDto> findAllOrders() {return purchaseService.findAllOrder();}

    @DeleteMapping("/deleteOrder/{id}")
    public void deleteOrder(@PathVariable(name = "id") Long id) {
        purchaseService.deleteOrder(id);
    }

    @PutMapping("/updateOrder/{id}")
    public PurchaseDto updateOrder(@RequestBody PurchaseDto orderdto, @PathVariable(name = "id") Long id){
        return purchaseService.updateOrder(orderdto);
    }

}
