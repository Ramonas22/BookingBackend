package codeacademy.bookingforum.app.user.auth.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserLoginDto {
    @NotEmpty(message = "Please enter your username or email")
    String unameOrEmail;
    @NotEmpty(message = "Please provide a password")
    String password;
}
