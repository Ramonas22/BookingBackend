package codeacademy.bookingforum.app.user.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserLoginDto {
    String unameOrEmail;
    String password;
}
