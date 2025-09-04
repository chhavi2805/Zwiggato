package com.zwiggato.FoodDelivery.controller;

import com.zwiggato.FoodDelivery.model.User;
import com.zwiggato.FoodDelivery.model.UserModel;
import com.zwiggato.FoodDelivery.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.logging.Logger;

@RestController
public class UserController {



    @Autowired
    private UserService userService;

    @PostMapping("createUser")
    public ResponseEntity<User> addUser(@RequestBody User user){
        Optional<User> userResponse = userService.createUser(user);
        if(userResponse.isPresent()){
            return ResponseEntity.created(null).body(userResponse.get());
        }
        return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("findByContact")
    public ResponseEntity<User> findUserByContact(@RequestBody @Valid User user){
        Optional<User> userResponse = userService.getUserbyContact(user);
        if(userResponse.isPresent()){
            return ResponseEntity.ok(userResponse.get());
        }
        return ResponseEntity.badRequest().body(null);
    }
    @PostMapping("changePassword")
    public ResponseEntity<UserModel> changePassword(@RequestBody @Valid UserModel user){
        Optional<User> userResponse = userService.changePassword(user);
        if(userResponse.isPresent()){
            return ResponseEntity.ok(UserModel.getUserModel(userResponse.get()));
        }
        return ResponseEntity.badRequest().body(null);
    }
}
