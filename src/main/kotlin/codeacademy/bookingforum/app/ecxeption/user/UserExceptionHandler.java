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
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    ResponseObject userNotFoundHandler(UserNotFoundException exception, WebRequest request) {
        return new ResponseObject(Collections.singletonList(exception.getMessage()),HttpStatus.NOT_FOUND,request);
    }

    @ResponseBody
    @ExceptionHandler(PasswordMismatchException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE) // 406
    ResponseObject handlePasswordMismatch(PasswordMismatchException exception, WebRequest request) {
        return new ResponseObject(Collections.singletonList(exception.getMessage()),HttpStatus.NOT_ACCEPTABLE,request);
    }

    @ResponseBody
    @ExceptionHandler(UsernameFormatException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE) // 406
    ResponseObject handleUsernameFormat(UsernameFormatException exception, WebRequest request) {
        List<String> errors = new ArrayList<>();
        errors.add("Username can contain characters: a-z, A-Z, 0-9, -, _");
        errors.add("Characters '-' and '_' can't be first or last, can't be repeated");
        errors.add("Username must be between 5 and 25 characters in length");
        return new ResponseObject(errors,HttpStatus.NOT_ACCEPTABLE,request);
    }

    @ResponseBody
    @ExceptionHandler(PasswordFormatException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE) // 406
    ResponseObject handlePasswordFormat(PasswordFormatException exception, WebRequest request) {
        List<String> errors = new ArrayList<>();
        errors.add("Password must contain at least one digit [0-9], lowercase letter [a-z], uppercase letter [A-Z].");
        errors.add("Password must contain at least one special character like ! @ # & ( ) (but no square brackets).");
        errors.add("Password length must be 8 to 30 characters total.");
        return new ResponseObject(errors,HttpStatus.NOT_ACCEPTABLE,request);
    }

    @ResponseBody
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT) // 409
    ResponseObject handleUserAlreadyExists(UserAlreadyExistsException exception, WebRequest request) {
        return new ResponseObject(Collections.singletonList(exception.getMessage()),HttpStatus.CONFLICT,request);
    }

    @ResponseBody
    @ExceptionHandler(AccountNotActivatedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN) // 403
    ResponseObject handleAccountNotActivated(AccountNotActivatedException exception, WebRequest request) {
        return new ResponseObject(Collections.singletonList(exception.getMessage()),HttpStatus.FORBIDDEN,request);
    }
}
