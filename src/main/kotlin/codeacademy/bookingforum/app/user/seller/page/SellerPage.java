package codeacademy.bookingforum.app.user.seller.page;

import codeacademy.bookingforum.app.order.Order;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import codeacademy.bookingforum.app.user.seller.rating.SellerRating;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Table(name = "seller_page")
@Entity
public class SellerPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private List<String> galleryLinks;

    private Integer min;

    private Integer max;

    private List<Date> unavailableDate;

    @OneToOne(mappedBy = "sellerpage")
    private UserAuth userauth;

    @ManyToMany(mappedBy = "ratingSeller")
    private List<SellerRating> sellerRate;

    @ManyToMany(mappedBy = "orderSeller")
    private List<Order> sellerOrder;

    public SellerPage() {

    }

    public SellerPage(Long id) {
        this.id = id;
    }

    public List<Date> getUnavailableDate() {
        return unavailableDate;
    }

    public void setUnavailableDate(List<Date> unavailableDate) {
        this.unavailableDate = unavailableDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getGalleryLinks() {
        return galleryLinks;
    }

    public void setGalleryLinks(List<String> galleryLinks) {
        this.galleryLinks = galleryLinks;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public UserAuth getUserauth() {
        return userauth;
    }

    public void setUserauth(UserAuth userauth) {
        this.userauth = userauth;
    }

    public List<SellerRating> getSellerRate() {
        return sellerRate;
    }

    public void setSellerRate(List<SellerRating> sellerRate) {
        this.sellerRate = sellerRate;
    }

    public List<Order> getSellerOrder() {
        return sellerOrder;
    }

    public void setSellerOrder(List<Order> sellerOrder) {
        this.sellerOrder = sellerOrder;
    }
}
