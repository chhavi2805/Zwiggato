package com.zwiggato.FoodDelivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @NonNull
    private String firstName;
    private String lastName;
    private Address address;
    @NonNull
    private String contact;
    private List<Order> orderHistory;
    private String role;
}
