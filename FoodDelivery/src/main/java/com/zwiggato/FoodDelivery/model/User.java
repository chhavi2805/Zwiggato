package com.zwiggato.FoodDelivery.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.GenericGenerator;

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
    private String address;
    private String password;
    @NotNull
    private String contact;
//    private List<Order> orderHistory;
    private String role;
}
