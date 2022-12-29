package codeacademy.bookingforum.app.ecxeption.user;

import codeacademy.bookingforum.app.configuration.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class UserExceptionHandler {
    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseObject userNotFoundHandler(UserNotFoundException exception, WebRequest request) {
        return new ResponseObject(new ArrayList<>(Collections.singletonList(exception.getMessage())),exception,request);
    }

    @ResponseBody
    @ExceptionHandler(PasswordMismatchException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    ResponseObject passwordMismatchHandler(PasswordMismatchException exception, WebRequest request) {
        return new ResponseObject(new ArrayList<>(Collections.singletonList(exception.getMessage())),exception,request);
    }

    @ResponseBody
    @ExceptionHandler(UsernameFormatException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    ResponseObject usernameFormatHandler(UsernameFormatException exception, WebRequest request) {
        List<String> errors = new ArrayList<>();
        errors.add("Username can contain characters: a-z, A-Z, 0-9, -, _");
        errors.add("Characters \"-\" and \"_\" can't be first or last, can't be repeated");
        errors.add("Username must be between 5 and 25 characters in length");
        return new ResponseObject(errors,exception,request);
    }

    @ResponseBody
    @ExceptionHandler(PasswordFormatException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    ResponseObject passwordFormatHandler(PasswordFormatException exception, WebRequest request) {
        List<String> errors = new ArrayList<>();
        errors.add("Password must contain at least one digit [0-9], lowercase letter [a-z], uppercase letter [A-Z].");
        errors.add("Password must contain at least one special character like ! @ # & ( ) (but no square brackets).");
        errors.add("Password length must be 8 to 30 characters total.");
        return new ResponseObject(errors,exception,request);
    }

    @ResponseBody
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    ResponseObject userAlreadyExistsHandler(UserAlreadyExistsException exception, WebRequest request) {
        return new ResponseObject(new ArrayList<>(Collections.singletonList(exception.getMessage())),exception,request);
    }
}
