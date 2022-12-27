package codeacademy.bookingforum.app.user.seller.page;

import codeacademy.bookingforum.app.purchase.Purchase;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import codeacademy.bookingforum.app.user.seller.rating.SellerRating;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Table(name = "seller_page")
@Entity
@NoArgsConstructor
public class SellerPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "gallery_links")
    private List<String> galleryLinks;
    @Column(name = "price_min")
    private Double priceMin;
    @Column(name = "price_max")
    private Double priceMax;
    @Column(name = "unavailable_dates")
    private List<Date> unavailableDates;
    @OneToOne(mappedBy = "sellerPage")
    private UserAuth userauth;
    @ManyToMany(mappedBy = "users")
    private List<SellerRating> sellerRatings;
    @ManyToMany(mappedBy = "sellers")
    private List<Purchase> purchases;

    public SellerPage(Long id) {
        this.id = id;
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

    public Double getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(Double priceMin) {
        this.priceMin = priceMin;
    }

    public Double getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(Double priceMax) {
        this.priceMax = priceMax;
    }

    public List<Date> getUnavailableDates() {
        return unavailableDates;
    }

    public void setUnavailableDates(List<Date> unavailableDates) {
        this.unavailableDates = unavailableDates;
    }

    public UserAuth getUserauth() {
        return userauth;
    }

    public void setUserauth(UserAuth userauth) {
        this.userauth = userauth;
    }

    public List<SellerRating> getSellerRatings() {
        return sellerRatings;
    }

    public void setSellerRatings(List<SellerRating> sellerRatings) {
        this.sellerRatings = sellerRatings;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }
}

