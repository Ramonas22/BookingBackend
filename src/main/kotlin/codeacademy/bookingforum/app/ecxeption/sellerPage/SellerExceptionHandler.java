package codeacademy.bookingforum.app.ecxeption.sellerPage;

import codeacademy.bookingforum.app.configuration.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;

@ControllerAdvice
public class SellerExceptionHandler {

    @ResponseBody
    @ExceptionHandler(PageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    ResponseObject handlePageNotFound(PageNotFoundException exception, WebRequest request) {
        return new ResponseObject(Collections.singletonList(exception.getMessage()), HttpStatus.NOT_FOUND, request);
    }
}
