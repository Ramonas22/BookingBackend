package codeacademy.bookingforum.app.configuration;

import codeacademy.bookingforum.app.user.enums.ResponseType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Setter
@Getter
public class ResponseObject {
    Map<ResponseType,String> responseType;
    String message;
    List<Object> data;
    String endpoint;
    LocalDateTime timestamp;

    // This constructor is only used for custom Exceptions
    public ResponseObject(String message, Object exception, WebRequest request) {
        this.responseType = new HashMap<>();
        this.responseType.put(ResponseType.ERROR, exception.getClass().getSimpleName());
        this.message = message;
        this.endpoint = ((ServletWebRequest)request).getRequest().getRequestURI();
        this.timestamp = LocalDateTime.now();
    }
}
