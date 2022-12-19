package codeacademy.bookingforum.app.user.seller.rating;

import codeacademy.bookingforum.app.user.auth.UserAuth;
import codeacademy.bookingforum.app.user.seller.page.SellerPage;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Table(name = "seller_rating")
@Entity
public class SellerRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rating;

    private String comment;

    private Date date;

    @ManyToMany
    @JoinTable(
            name = "user_seller_rank",
            joinColumns = @JoinColumn(name = "sellerrating_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserAuth> userRating;


    @ManyToMany
    @JoinTable(
            name = "Sellrating_sellerPage",
            joinColumns = @JoinColumn(name = "sellerrating_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<SellerPage> ratingSeller;

    public SellerRating() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<UserAuth> getUserRating() {
        return userRating;
    }

    public void setUserRating(List<UserAuth> userRating) {
        this.userRating = userRating;
    }

    public List<SellerPage> getRatingSeller() {
        return ratingSeller;
    }

    public void setRatingSeller(List<SellerPage> ratingSeller) {
        this.ratingSeller = ratingSeller;
    }
}
