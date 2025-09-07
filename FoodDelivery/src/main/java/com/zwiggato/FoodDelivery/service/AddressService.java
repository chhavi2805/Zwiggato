package com.zwiggato.FoodDelivery.service;

import com.zwiggato.FoodDelivery.model.Address;
import com.zwiggato.FoodDelivery.model.User;
import com.zwiggato.FoodDelivery.repositiory.AddressRepository;
import com.zwiggato.FoodDelivery.repositiory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserRepository userRepository;

    public List<Address> addAddress(String userId,Address address){
        Optional<User> user=userRepository.findById(userId);
        if(user.isPresent()){
            User u = user.get();
            address.setUser(u);
            addressRepository.save(address);
            List<Address> addresses = addressRepository.findAddByUserUserId(userId);
            for(Address a: addresses){
                a.setUser(null);
            }
            return addresses;

        }
        return null;
    }
}
