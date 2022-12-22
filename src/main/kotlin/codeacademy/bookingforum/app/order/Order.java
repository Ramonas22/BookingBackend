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
    @Column(name = "id")
    private Long id;
    @Column(name = "details")
    private String details;
    @Column(name = "booked_date")
    private Date bookedDate;

    @ManyToMany
    @JoinTable(
            name = "user_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserAuth> users;

    @ManyToMany
    @JoinTable(
            name = "seller_order",
            joinColumns = @JoinColumn(name = "seller_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<SellerPage> sellers;

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

    public List<UserAuth> getUsers() {
        return users;
    }

    public void setUsers(List<UserAuth> users) {
        this.users = users;
    }

    public List<SellerPage> getSellers() {
        return sellers;
    }

    public void setSellers(List<SellerPage> sellers) {
        this.sellers = sellers;
    }
}
