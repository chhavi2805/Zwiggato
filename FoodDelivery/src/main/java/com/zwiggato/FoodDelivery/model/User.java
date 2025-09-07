package com.zwiggato.FoodDelivery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "user-id-generator")
    @GenericGenerator(name = "user-id-generator", strategy = "com.zwiggato.FoodDelivery.utils.UserIdGenerator")
    private String userId;

    @NotNull
    private String firstName;
    private String lastName;
    private String password;
    @NotNull
    private String contact;
//    private List<Order> orderHistory;
    private String role;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Address> address = new ArrayList<>();

}
