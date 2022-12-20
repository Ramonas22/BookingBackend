package codeacademy.bookingforum.app.order;

import codeacademy.bookingforum.app.user.auth.UserAuth;
import codeacademy.bookingforum.app.user.seller.page.SellerPage;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Table(name = "order")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String details;

    private Date bookedDate;

    @ManyToMany
    @JoinTable(
            name = "user_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserAuth> orderUser;

    @ManyToMany
    @JoinTable(
            name = "seller_order",
            joinColumns = @JoinColumn(name = "seller_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<SellerPage> orderSeller;

    public Order() {}

    public Order(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(Date bookedDate) {
        this.bookedDate = bookedDate;
    }

    public List<UserAuth> getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(List<UserAuth> orderUser) {
        this.orderUser = orderUser;
    }

    public List<SellerPage> getOrderSeller() {
        return orderSeller;
    }

    public void setOrderSeller(List<SellerPage> orderSeller) {
        this.orderSeller = orderSeller;
    }
}
