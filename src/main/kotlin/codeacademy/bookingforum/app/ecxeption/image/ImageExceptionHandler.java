package codeacademy.bookingforum.app.ecxeption.image;

import codeacademy.bookingforum.app.configuration.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;

@ControllerAdvice
public class ImageExceptionHandler {
    @ResponseBody
    @ExceptionHandler(ImageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE) // 406
    ResponseObject handleImageNotFound(ImageNotFoundException exception, WebRequest request) {
        return new ResponseObject(Collections.singletonList(exception.getMessage()), HttpStatus.NOT_ACCEPTABLE, request);
    }

    @ResponseBody
    @ExceptionHandler(UnsupportedImageFormatException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE) // 406
    ResponseObject handleUnsupportedImageFormat(UnsupportedImageFormatException exception, WebRequest request) {
        return new ResponseObject(Collections.singletonList(exception.getMessage()), HttpStatus.NOT_ACCEPTABLE, request);
    }

    @ResponseBody
    @ExceptionHandler(DirectoryCreationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseObject handleDirectoryCreation(DirectoryCreationException exception, WebRequest request) {
        return new ResponseObject(Collections.singletonList(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
