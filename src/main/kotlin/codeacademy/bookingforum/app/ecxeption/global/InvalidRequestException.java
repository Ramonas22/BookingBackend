package codeacademy.bookingforum.app.ecxeption.global;

public class InvalidRequestException extends RuntimeException{
    public InvalidRequestException(String message){super(message);}
    public InvalidRequestException() {super("Invalid request.");}
}
