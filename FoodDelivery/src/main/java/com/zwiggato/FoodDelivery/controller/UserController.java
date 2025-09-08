package com.zwiggato.FoodDelivery.controller;

import com.zwiggato.FoodDelivery.model.User;
import com.zwiggato.FoodDelivery.model.UserModel;
import com.zwiggato.FoodDelivery.service.UserService;
import com.zwiggato.FoodDelivery.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;
    @PostMapping("createUser")
    public ResponseEntity<Object> addUser(@RequestBody User user){
        Optional<User> userResponse = userService.createUser(user);
        return userResponse.isPresent()? ResponseEntity.status(HttpStatus.CREATED).body(userResponse.get()) :
                ResponseEntity.badRequest().body("User Already exists");

    }
    @PostMapping("/authLogin")
    public String login(@Valid @RequestBody UserModel authRequest) {
        Optional<User> user = userService.validateUser(authRequest);
        if(user.isEmpty()){
            return "Invalid Credentials or User not exists";
        }
        User userDb = user.get();
        return jwtUtil.generateToken(userDb.getContact());
    }

    
    @PostMapping("findByContact")
    public ResponseEntity<?> findUserByContact(@RequestBody @Valid UserModel user){
        Optional<User> userResponse = userService.getUserByContact(user.getContact());
        if(userResponse.isPresent()){
            return ResponseEntity.ok(UserModel.getUserModel(userResponse.get()));
        }
        return ResponseEntity.badRequest().body("User does not exist");
    }
    @PostMapping("changePassword")
    public ResponseEntity<?> changePassword(@RequestBody @Valid UserModel user){
        Optional<User> userResponse = userService.changePassword(user);
        if(userResponse.isPresent()){
            return ResponseEntity.ok(UserModel.getUserModel(userResponse.get()));
        }
        return ResponseEntity.badRequest().body("User password mismatch or user not found");
    }
}
