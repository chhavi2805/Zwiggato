package com.zwiggato.FoodDelivery.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
@ToString(exclude = {"user"})
public class Address {
    @Id
    @GeneratedValue(generator = "add-id-generator")
    @GenericGenerator(name = "add-id-generator", strategy = "com.zwiggato.FoodDelivery.utils.AddressIdGenerator")
    private String addId;

    private String name;
    private String line1;
    private String line2;
    private String city;
    private String pincode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
