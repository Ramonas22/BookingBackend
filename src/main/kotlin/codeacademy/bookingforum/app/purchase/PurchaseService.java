package codeacademy.bookingforum.app.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PurchaseService {

    @Autowired
    PurchaseRepo orderrepo;

    @Autowired
    PurchaseMapper ordermapper;


    public PurchaseDto findById(Long id){

        Purchase purchase = orderrepo.findById(id).orElse(null);
        return ordermapper.toDto(purchase);
    }

    public List<PurchaseDto> findAllOrder(){
        List<Purchase> purchases = (List<Purchase>) orderrepo.findAll();

        return ordermapper.toDto(purchases);
    }

    public PurchaseDto createOrder(PurchaseDto purchaseDto){
        Purchase purchase = ordermapper.fromDto(purchaseDto);
        orderrepo.save(purchase);
        return ordermapper.toDto(purchase);
    }

    public PurchaseDto updateOrder(PurchaseDto purchaseDto){
        Purchase purchase = ordermapper.fromDto(purchaseDto);
        orderrepo.save(purchase);
        return ordermapper.toDto(purchase);
    }

    public void deleteOrder(Long id){
        orderrepo.deleteById(id);
    }







}
