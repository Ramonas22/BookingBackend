package codeacademy.bookingforum.app.user.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDetailsDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String biography;
    private String gender;
}
