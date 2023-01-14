package codeacademy.bookingforum.app.user.seller.rating;

import codeacademy.bookingforum.app.user.auth.UserAuth;
import codeacademy.bookingforum.app.user.seller.page.SellerPage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "seller_rating")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SellerRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    UserAuth user;
    @ManyToOne
    @JoinColumn(name = "page_id", nullable = false)
    SellerPage page;
    @Column(name = "rating")
    short rating;
    @Column(name = "comment")
    String comment;
    @Column(name = "date")
    LocalDateTime date;

    public SellerRating(Long id) {
        this.id = id;
    }
}
