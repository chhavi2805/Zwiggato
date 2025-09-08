package com.zwiggato.FoodDelivery.service;

import com.zwiggato.FoodDelivery.model.Address;
import com.zwiggato.FoodDelivery.model.User;
import com.zwiggato.FoodDelivery.repositiory.AddressRepository;
import com.zwiggato.FoodDelivery.repositiory.UserRepository;
import com.zwiggato.FoodDelivery.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserRepository userRepository;

    @Transactional
    public List<Address> addAddress(String contact,Address address){
        User user=userRepository.findByContact(contact).get();
        List<Address> addresses = user.getAddress();
        for(Address a : addresses){
            if(a.getName().equals(address.getName())){
                return new ArrayList<>();
            }
        }
        user.getAddress();
        address.setUser(user);
        addresses.add(addressRepository.save(address));
        for(Address a: addresses){
            a.setUser(null);
        }
        return addresses;

    }
}
