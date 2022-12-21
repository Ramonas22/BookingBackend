package codeacademy.bookingforum.app.user.auth;

import codeacademy.bookingforum.app.user.enums.Gender;
import codeacademy.bookingforum.app.user.role.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;

@Table(name = "user_auth")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class UserAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "gender")
    private Gender gender;
    @Column(name = "biography")
    private String biography;
    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;
    @Column(name = "enabled")
    private boolean enabled;
    @Column(name = "join_date")
    private LocalDateTime joinDate;

    public UserAuth(Long id) {
        this.id = id;
    }
}
