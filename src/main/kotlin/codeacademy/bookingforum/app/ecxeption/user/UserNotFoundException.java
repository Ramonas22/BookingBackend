package codeacademy.bookingforum.app.ecxeption.user;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){super(message);}
}
