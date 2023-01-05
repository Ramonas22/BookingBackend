package codeacademy.bookingforum.app.user.role;

import codeacademy.bookingforum.app.user.auth.UserAuth;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;
    @Column(name = "display_name", unique = true)
    private String displayName;
    @ManyToMany(mappedBy = "roles")
    private List<UserAuth> users;

    public Role(String displayName) {
        this.displayName = displayName;
    }
}
