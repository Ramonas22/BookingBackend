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

    public PurchaseDto updateOrder(PurchaseDto purchaseDto){
        Purchase purchase = purchaseMapper.fromDto(purchaseDto);
        purchaseRepo.save(purchase);
        return purchaseMapper.toDto(purchase);
    }

    public void deleteOrder(Long id){
        purchaseRepo.deleteById(id);
    }







}
