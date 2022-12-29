package codeacademy.bookingforum.app.user.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserAuthDto {
    @NotEmpty(message = "Please provide a username")
    private String username;
    @NotEmpty(message = "Please provide your email")
    @Email(message = "Provided email is not valid")
    private String email;
    @NotEmpty(message = "Please provide a password")
    private String password;
    @NotEmpty(message = "Please repeat your password")
    private String repeatPassword;
    private String gender;

}
