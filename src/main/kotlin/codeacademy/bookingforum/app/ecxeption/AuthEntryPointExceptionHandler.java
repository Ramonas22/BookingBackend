package codeacademy.bookingforum.app.ecxeption;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

@Component
public class AuthEntryPointExceptionHandler implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointExceptionHandler.class);


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException exception) throws IOException, ServletException {
        // 401 HTTP Response
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
    }

    @ExceptionHandler (value = {AccessDeniedException.class})
    public void commence(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException {
        // 403 HTTP Response
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Authorization Failed: " + exception.getMessage());
    }

    @ExceptionHandler (value = {Exception.class})
    public void commence(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        // 500 HTTP Response
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error: " + exception.getMessage());
    }
}
