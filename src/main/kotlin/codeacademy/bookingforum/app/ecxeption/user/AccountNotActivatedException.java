package codeacademy.bookingforum.app.ecxeption.user;

import java.io.IOException;

public class AccountNotActivatedException extends RuntimeException {
    public AccountNotActivatedException(String message) {super(message);}
}
