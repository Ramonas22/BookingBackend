package codeacademy.bookingforum.app.user.auth.dto;

import codeacademy.bookingforum.app.purchase.PurchaseDto;
import codeacademy.bookingforum.app.user.role.RoleDto;
import codeacademy.bookingforum.app.user.seller.page.SellerPageDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserManagementDto {
    private Long id;
    private String username;
    private String email;
    private String gender;
    private String biography;
    private List<String> roles;
    private List<PurchaseDto> purchases;
    private boolean enabled;
    private LocalDateTime joinDate;
}


