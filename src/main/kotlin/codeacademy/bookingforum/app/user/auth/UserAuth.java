package codeacademy.bookingforum.app.user.auth;

import codeacademy.bookingforum.app.purchase.Purchase;
import codeacademy.bookingforum.app.user.enums.Gender;
import codeacademy.bookingforum.app.user.role.Role;
import codeacademy.bookingforum.app.user.seller.page.SellerPage;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

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
    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;
    @ManyToMany(mappedBy = "users")
    private List<Purchase> purchases;
    @Column(name = "enabled")
    private boolean enabled;
    @Column(name = "join_date")
    private LocalDateTime joinDate;

    @OneToOne
    @JoinColumn(name = "seller_id")
    private SellerPage sellerPage;




    public UserAuth(Long id) {
        this.id = id;
    }

    public UserAuth(String username, String password, Collection<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuth userAuth = (UserAuth) o;
        return enabled == userAuth.enabled && id.equals(userAuth.id) && username.equals(userAuth.username) && email.equals(userAuth.email) && password.equals(userAuth.password) && gender == userAuth.gender && Objects.equals(biography, userAuth.biography) && roles.equals(userAuth.roles) && Objects.equals(purchases, userAuth.purchases) && joinDate.equals(userAuth.joinDate) && Objects.equals(sellerPage, userAuth.sellerPage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, gender, biography, roles, purchases, enabled, joinDate, sellerPage);
    }

    @Override
    public String toString() {
        return "UserAuth{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", biography='" + biography + '\'' +
                ", roles=" + roles +
                ", purchases=" + purchases +
                ", enabled=" + enabled +
                ", joinDate=" + joinDate +
                ", sellerPage=" + sellerPage +
                '}';
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

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
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
