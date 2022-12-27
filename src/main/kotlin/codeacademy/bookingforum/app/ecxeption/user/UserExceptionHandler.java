package codeacademy.bookingforum.app.ecxeption.user;

import codeacademy.bookingforum.app.configuration.ResponseObject;
import codeacademy.bookingforum.app.ecxeption.user.UserNotFoundException;
import codeacademy.bookingforum.app.user.enums.ResponseType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class UserExceptionHandler {
    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseObject userNotFoundHandler(UserNotFoundException e, WebRequest request) {
//        Map<ResponseType,String> response = new HashMap<>();
//        response.put(ResponseType.ERROR,"UserNotFoundException");
//
//        ResponseObject res = new ResponseObject();
//        res.setResponseType(response);
//        res.setMessage(e.getMessage());
//        res.setEndpoint(((ServletWebRequest)request).getRequest().getRequestURI());
//        res.setTimestamp(LocalDateTime.now());
//
//        return res;

        return new ResponseObject(e.getMessage(),e,request);
    }
}
