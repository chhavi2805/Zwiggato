package com.zwiggato.FoodDelivery.controller;

import com.zwiggato.FoodDelivery.model.Address;
import com.zwiggato.FoodDelivery.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("createAddress/{userid}")
    public ResponseEntity<List<Address>> addAddress(@RequestBody Address address, @PathVariable String userid){

        List<Address> addList = addressService.addAddress(userid, address);
        if(addList!=null){
            return ResponseEntity.ok(addList);
        }
        return ResponseEntity.badRequest().body(null);

    }
}
