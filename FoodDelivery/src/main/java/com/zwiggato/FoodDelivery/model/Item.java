package com.zwiggato.FoodDelivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor

public class Item {
    @NonNull
    private String itemCode;
    @NonNull
    private String itemName;
    private Double itemPrice;
    private String description;
}
