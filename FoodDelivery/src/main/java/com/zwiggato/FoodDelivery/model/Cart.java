package com.zwiggato.FoodDelivery.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {
    private List<Item> cartItem;

    public Cart() {
        this.cartItem = new ArrayList<>();
    }

    public void addToCart(Item item){
        cartItem.add(item);
    }
}
