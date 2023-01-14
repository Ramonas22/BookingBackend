package codeacademy.bookingforum.app.ecxeption.global;

import codeacademy.bookingforum.app.configuration.ResponseObject;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE) // 406
    ResponseObject handleMethodArgumentNotValid(MethodArgumentNotValidException exception, WebRequest request) {
        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return new ResponseObject(errors, HttpStatus.NOT_ACCEPTABLE, request);
    }

    @ResponseBody
    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    ResponseObject invalidRequestHandler(InvalidRequestException exception, WebRequest request) {
        return new ResponseObject(Collections.singletonList(exception.getMessage()), HttpStatus.BAD_REQUEST, request);
    }

    @ResponseBody
    @ExceptionHandler(UnsatisfiedExpectationException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE) // 406
    ResponseObject unsatisfiedExpectationHandler(UnsatisfiedExpectationException exception, WebRequest request) {
        return new ResponseObject(Collections.singletonList(exception.getMessage()), HttpStatus.NOT_ACCEPTABLE, request);
    }
}
