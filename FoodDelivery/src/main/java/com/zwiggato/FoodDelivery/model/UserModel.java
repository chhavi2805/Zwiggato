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
    private String firstName;
    private String lastName;
    private String password;
    private String newPassword;
    @NotNull
    private String contact;
    private List<Address> address;

    public static UserModel getUserModel(User u){
        UserModel um = new UserModel();
        um.setFirstName(u.getFirstName());
        um.setLastName(u.getLastName());
        um.setContact(u.getContact());
        um.setPassword("*******");
        List<Address> addresses = u.getAddress();
        for(Address a: addresses)
            a.setUser(null);
        um.setAddress(addresses);
        um.setNewPassword(null);
        return um;
    }
}
