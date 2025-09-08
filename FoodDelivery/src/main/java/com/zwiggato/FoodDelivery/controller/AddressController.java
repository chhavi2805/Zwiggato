package com.zwiggato.FoodDelivery.controller;

import com.zwiggato.FoodDelivery.model.Address;
import com.zwiggato.FoodDelivery.security.JwtUtil;
import com.zwiggato.FoodDelivery.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("createAddress/")
    public ResponseEntity<?> addAddress(@RequestBody Address address, @RequestHeader("Authorization") String auth){
        String contact = jwtUtil.extractContact(auth);
        System.out.println("\n\n=====================Adding new Address for "+ contact+ "=============================\n");

        List<Address> addList = addressService.addAddress(contact, address);
        if(addList!=null){
            return ResponseEntity.ok(addList);
        }
        return ResponseEntity.status(409).body("Address not Added");
    }
}
