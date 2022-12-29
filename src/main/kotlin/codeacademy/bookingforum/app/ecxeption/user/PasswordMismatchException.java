package codeacademy.bookingforum.app.ecxeption.user;

public class PasswordMismatchException extends RuntimeException{
    public PasswordMismatchException(String message) {super(message);}
}
