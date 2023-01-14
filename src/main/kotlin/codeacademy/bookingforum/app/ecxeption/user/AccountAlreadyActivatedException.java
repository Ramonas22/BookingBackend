package codeacademy.bookingforum.app.ecxeption.user;

public class AccountAlreadyActivatedException extends RuntimeException{
    public AccountAlreadyActivatedException(String message) {super(message);}
}
