package codeacademy.bookingforum.app.user.auth;

import codeacademy.bookingforum.app.image.Image;
import codeacademy.bookingforum.app.purchase.Purchase;
import codeacademy.bookingforum.app.enums.Gender;
import codeacademy.bookingforum.app.user.role.Role;
import codeacademy.bookingforum.app.user.seller.page.SellerPage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "user_auth")
@Entity
@NoArgsConstructor
public class UserAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;
    @Column(name = "biography")
    private String biography;
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;
    @JsonIgnore
    @ManyToMany(mappedBy = "users")
    private List<Purchase> purchases;
    @Column(name = "enabled")
    private boolean enabled;
    @Column(name = "join_date")
    private LocalDateTime joinDate;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "seller_id")
    private SellerPage sellerPage;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Image> images;




    public UserAuth(Long id) {
        this.id = id;
    }

    public UserAuth(String username, String password, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDateTime joinDate) {
        this.joinDate = joinDate;
    }

    public SellerPage getSellerPage() {
        return sellerPage;
    }

    public void setSellerPage(SellerPage sellerPage) {
        this.sellerPage = sellerPage;
    }
}
