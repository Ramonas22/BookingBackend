package codeacademy.bookingforum.app.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class ResponseObject {
    LocalDateTime timestamp;
    int status;
    List<String> messages = new ArrayList<>();
    String endpoint;


    // Used for informational and error responses
    public ResponseObject(List<String> messages, HttpStatus status, WebRequest request) {
        this.status = status.value();
        this.messages = messages;
        this.endpoint = ((ServletWebRequest)request).getRequest().getRequestURI();
        this.timestamp = LocalDateTime.now();
    }
}
