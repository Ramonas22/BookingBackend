package codeacademy.bookingforum.app.configuration;

import codeacademy.bookingforum.app.user.enums.ResponseType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class ResponseObject {
    ResponseType status;
    String error;
    List<String> messages = new ArrayList<>();
    Object data;
    String endpoint;
    LocalDateTime timestamp;

    // Used for error responses
    public ResponseObject(List<String> messages, Object exception, WebRequest request) {
        this.status = ResponseType.ERROR;
        this.error = exception.getClass().getSimpleName();
        this.messages = messages;
        this.endpoint = ((ServletWebRequest)request).getRequest().getRequestURI();
        this.timestamp = LocalDateTime.now();
    }

    // Used for informational responses
    public ResponseObject(String message, WebRequest request) {
        this.status = ResponseType.INFO;
        this.messages.add(message);
        this.endpoint = ((ServletWebRequest)request).getRequest().getRequestURI();
        this.timestamp = LocalDateTime.now();
    }

    // Used for data responses

    public ResponseObject(String message, Object data, WebRequest request) {
        this.status = ResponseType.DATA;
        this.messages.add(message);
        this.data = data;
        this.endpoint = ((ServletWebRequest)request).getRequest().getRequestURI();
        this.timestamp = LocalDateTime.now();
    }
}
