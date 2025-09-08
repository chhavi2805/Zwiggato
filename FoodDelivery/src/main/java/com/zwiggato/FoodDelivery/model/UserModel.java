package com.zwiggato.FoodDelivery.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserModel {
    private String userId;
    private String firstName;
    private String lastName;
    private String password;
    private String newPassword;
    private String roles;
    @NotNull
    private String contact;
    private List<Address> address;

    public static UserModel getUserModel(User u){
        UserModel um = new UserModel();
        um.setUserId(u.getUserId());
        um.setFirstName(u.getFirstName());
        um.setLastName(u.getLastName());
        um.setContact(u.getContact());
        um.setRoles(u.getRole());
        um.setPassword("*******");
        List<Address> addresses = u.getAddress();
        if(addresses != null) {
            for(Address a: addresses)
                a.setUser(null);
        }
        um.setAddress(addresses);
        um.setNewPassword(null);
        return um;
    }
}
