package codeacademy.bookingforum.app.purchase;

import codeacademy.bookingforum.app.user.auth.UserAuth;
import codeacademy.bookingforum.app.user.seller.page.SellerPage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "purchase")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "details")
    private String details;
    @Column(name = "booked_date")
    private LocalDateTime bookedDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserAuth user;

    @ManyToOne
    @JoinColumn(name = "page_id", nullable = false)
    private SellerPage sellerPage;

}
