package code.Academy.BookingApp.Entity;

import code.Academy.BookingApp.role.entity.Roles;
import lombok.Data;


import javax.persistence.Entity;


@Data
@Entity
public class Users {


    ////fdgfdgdfg

    private Roles roles = new Roles(1, "");

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

}
