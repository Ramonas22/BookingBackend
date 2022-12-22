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
    private String displayName;
    @ManyToMany(mappedBy = "roles")
    private Collection<UserAuth> users;

    public Role() {
    }

    public Role(String displayName) {
        this.displayName = displayName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Collection<UserAuth> getUsers() {
        return users;
    }

    public void setUsers(List<UserAuth> users) {
        this.users = users;
    }
}
