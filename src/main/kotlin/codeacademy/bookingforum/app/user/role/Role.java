package codeacademy.bookingforum.app.user.role;

import codeacademy.bookingforum.app.user.auth.UserAuth;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "display_name")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
    @ManyToMany(mappedBy = "roles")
    private List<UserAuth> users;

    public Role() {
    }

    public Role(RoleEnum role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public Collection<UserAuth> getUsers() {
        return users;
    }

    public void setUsers(List<UserAuth> users) {
        this.users = users;
    }
}
