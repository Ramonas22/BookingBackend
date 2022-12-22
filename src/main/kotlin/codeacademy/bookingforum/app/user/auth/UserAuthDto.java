package codeacademy.bookingforum.app.user.auth;

import codeacademy.bookingforum.app.user.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserAuthDto {
    @NotEmpty(message = "Please choose a username")
    private String username;
    @NotEmpty(message = "Please provide your email")
    @Email(message = "Entered email is not valid")
    private String email;
    @NotEmpty(message = "Please provide a password")
    private String password;
    @NotEmpty(message = "Please repeat your password")
    private String repeatPassword;
    private Gender gender;
}
