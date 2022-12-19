package codeacademy.bookingforum.app.user.auth;

import codeacademy.bookingforum.app.order.Order;
import codeacademy.bookingforum.app.photo.Photo;
import codeacademy.bookingforum.app.user.enums.Gender;
import codeacademy.bookingforum.app.user.role.Role;
import codeacademy.bookingforum.app.user.seller.page.SellerPage;
import codeacademy.bookingforum.app.user.seller.rating.SellerRating;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

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


    @OneToOne(cascade = CascadeType.ALL)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id")
    private SellerPage sellerpage;
    @ManyToMany(mappedBy = "userRating")
    private List<SellerRating> sellerRait;

    @ManyToMany(mappedBy = "orderUser")
    private List<Order> userOrder;

    @ManyToMany(mappedBy = "userPhotos")
    private List<Photo> photoUsers;

    @ManyToMany(mappedBy = "userRating")
    private List<SellerRating> sellerRating;

    public UserAuth() {
    }

    public List<SellerRating> getSellerRating() {
        return sellerRating;
    }

    public void setSellerRating(List<SellerRating> sellerRating) {
        this.sellerRating = sellerRating;
    }

    public UserAuth(Long id) {
        this.id = id;
    }

    public List<SellerRating> getSellerRait() {
        return sellerRait;
    }

    public void setSellerRait(List<SellerRating> sellerRait) {
        this.sellerRait = sellerRait;
    }

    public List<Order> getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(List<Order> userOrder) {
        this.userOrder = userOrder;
    }

    public List<Photo> getPhotoUsers() {
        return photoUsers;
    }

    public void setPhotoUsers(List<Photo> photoUsers) {
        this.photoUsers = photoUsers;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
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
