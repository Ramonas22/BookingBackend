package codeacademy.bookingforum.app.ecxeption.sellerPage;

public class PurchaseNotFoundException extends RuntimeException{
    public PurchaseNotFoundException(String message) {super(message);}
}
