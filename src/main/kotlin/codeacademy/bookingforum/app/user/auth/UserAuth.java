package codeacademy.bookingforum.app.user.auth;

import codeacademy.bookingforum.app.user.enums.Gender;
import codeacademy.bookingforum.app.user.role.Role;
import codeacademy.bookingforum.app.user.seller.page.SellerPage;
import jakarta.persistence.*;

import java.util.Collection;

@Table(name = "user_auth")
@Entity
public class UserAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private Gender gender;
    private boolean enabled;
    private boolean tokenExpired;
    private String biography;
    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id")
    private SellerPage sellerpage;

    public UserAuth() {
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isTokenExpired() {
        return tokenExpired;
    }

    public void setTokenExpired(boolean tokenExpired) {
        this.tokenExpired = tokenExpired;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public SellerPage getSellerpage() {
        return sellerpage;
    }

    public void setSellerpage(SellerPage sellerpage) {
        this.sellerpage = sellerpage;
    }
}
