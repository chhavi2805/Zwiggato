package com.zwiggato.FoodDelivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    private String restId;
    private String restName;
    private String restContact;
    private Menu menuItems;
}
