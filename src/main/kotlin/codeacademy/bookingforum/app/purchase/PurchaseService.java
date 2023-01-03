package codeacademy.bookingforum.app.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PurchaseService {

    @Autowired
    PurchaseRepo purchaseRepo;

    @Autowired
    PurchaseMapper purchaseMapper;


    public PurchaseDto findById(Long id){

        Purchase purchase = purchaseRepo.findById(id).orElse(null);
        return purchaseMapper.toDto(purchase);
    }

    public List<PurchaseDto> findAllOrder(){
        List<Purchase> purchases = (List<Purchase>) purchaseRepo.findAll();

        return purchaseMapper.toDtoList(purchases);
    }

    public PurchaseDto createOrder(PurchaseDto purchaseDto){
        Purchase purchase = purchaseMapper.fromDto(purchaseDto);
        purchaseRepo.save(purchase);
        return purchaseMapper.toDto(purchase);
    }

    public String updateOrder(Long id, PurchaseDto purchaseDto){
        if(purchaseRepo.existsById(id)) {
            Purchase purchase = purchaseMapper.fromDto(purchaseDto);
            purchase.setId(id);
            purchaseRepo.save(purchase);
            return "Order with id " + id + " updated";
        }else {
            return "Order with id " + id + " not found";
        }
    }

    public String deleteOrder(Long id){
        if(purchaseRepo.existsById(id)) {
            purchaseRepo.deleteById(id);
            return "Order with id " + id + " deleted";
        }else {
            return "Order with id " + id + " does not exist";
        }
    }







}
