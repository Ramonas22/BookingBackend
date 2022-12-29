package codeacademy.bookingforum.app.ecxeption.user;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String message) {super(message);}
}
