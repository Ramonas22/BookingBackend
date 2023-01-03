package codeacademy.bookingforum.app.user.seller.rating;

import codeacademy.bookingforum.app.user.auth.UserAuth;
import codeacademy.bookingforum.app.user.seller.page.SellerPage;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "seller_rating")
@Entity
@NoArgsConstructor
public class SellerRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToMany
    @JoinTable(
            name = "user_seller_rating",
            joinColumns = @JoinColumn(name = "seller_rating_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserAuth> users;
    @ManyToMany
    @JoinTable(
            name = "seller_page_rating",
            joinColumns = @JoinColumn(name = "seller_rating_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<SellerPage> sellerPages;
    @Column(name = "rating")
    private Double rating;
    @Column(name = "comment")
    private String comment;
    @Column(name = "date")
    private LocalDateTime date;

    public SellerRating(Long id) {
        this.id = id;
    }

    public SellerRating(Long id, List<UserAuth> users, List<SellerPage> sellerPages, Double rating, String comment, LocalDateTime date) {
        this.id = id;
        this.users = users;
        this.sellerPages = sellerPages;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UserAuth> getUsers() {
        return users;
    }

    public void setUsers(List<UserAuth> users) {
        this.users = users;
    }

    public List<SellerPage> getSellerPages() {
        return sellerPages;
    }

    public void setSellerPages(List<SellerPage> sellerPages) {
        this.sellerPages = sellerPages;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
