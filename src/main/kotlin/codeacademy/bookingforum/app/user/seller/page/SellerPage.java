package codeacademy.bookingforum.app.user.seller.page;

import codeacademy.bookingforum.app.purchase.Purchase;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import codeacademy.bookingforum.app.user.seller.rating.SellerRating;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Table(name = "seller_page")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SellerPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "description")
    String description;
    @Column(name = "gallery_image_ids")
    List<Long> galleryImageIds;
    @Column(name = "price_min")
    Double priceMin;
    @Column(name = "price_max")
    Double priceMax;
    @Column(name = "unavailable_dates")
    List<Date> unavailableDates;
    @OneToOne(mappedBy = "sellerPage")
    UserAuth user;
    @OneToMany(mappedBy = "page")
    List<SellerRating> ratings;
    @ManyToMany(mappedBy = "sellers")
    List<Purchase> purchases;

    public SellerPage(Long id) {
        this.id = id;
    }
    public SellerPage(UserAuth user) {
        this.description = "Welcome to "+user.getUsername()+"'s page!";
        this.priceMin = 1.0;
        this.priceMax = 10.0;
        this.user = user;
    }
}

