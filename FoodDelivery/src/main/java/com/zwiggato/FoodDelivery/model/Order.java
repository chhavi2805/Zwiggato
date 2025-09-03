package com.zwiggato.FoodDelivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String orderId;
    private List<Item> orderItem;
    private Double totalAmount;

    public Double calculateTotalAmount() {
        Double amount=0.0;
        for(Item i: orderItem)
            amount+=i.getItemPrice();
        this.totalAmount=amount;
        return totalAmount;
    }
}
