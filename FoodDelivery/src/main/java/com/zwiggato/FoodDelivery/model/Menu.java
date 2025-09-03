package com.zwiggato.FoodDelivery.model;

import lombok.Data;

import java.util.List;
@Data

public class Menu {
    private Restaurant restaurant;
    private List<Item> foodItems;

    public Menu(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
