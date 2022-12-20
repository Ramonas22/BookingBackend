package codeacademy.bookingforum.app.user.auth;

import codeacademy.bookingforum.app.user.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserAuthDto {
    private String username;
    private String email;
    private String password;
    private Gender gender;
}
