package codeacademy.bookingforum.app.user.auth;

import codeacademy.bookingforum.app.user.role.Role;
import codeacademy.bookingforum.app.user.seller.page.SellerPage;
import jakarta.persistence.*;

import java.util.Collection;

public class UserAuthDto {
    private String username;
    public UserAuthDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
