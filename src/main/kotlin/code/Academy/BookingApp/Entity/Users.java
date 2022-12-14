package code.Academy.BookingApp.Entity;

import code.Academy.BookingApp.role.controller.RolesController;
import code.Academy.BookingApp.role.dto.RolesDto;
import code.Academy.BookingApp.role.entity.Roles;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;


import javax.persistence.Entity;


@Data
@Entity
public class Users {


    @Autowired
    RolesController controller;

    ////fdgfdgdfg

    private  int interis = 0;

    private Roles roles = new Roles(1, "");
    public void printMe(){
        System.out.println("I am here");
        controller.getResult();
        controller.test();
        RolesController cmt = new RolesController();
        cmt.test();
    }

    public void metodas(){
        controller.getResult();
        RolesDto dto = new RolesDto();

    }



}
