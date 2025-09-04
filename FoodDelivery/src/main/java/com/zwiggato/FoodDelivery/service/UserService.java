package com.zwiggato.FoodDelivery.service;

import com.zwiggato.FoodDelivery.controller.UserController;
import com.zwiggato.FoodDelivery.model.User;
import com.zwiggato.FoodDelivery.model.UserModel;
import com.zwiggato.FoodDelivery.repositiory.UserRepository;
import com.zwiggato.FoodDelivery.utils.PasswordEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> createUser(User user) {
        Optional<User> check = userRepository.findByContact(user.getContact());
        if(check.isEmpty()) {
            User userResponse = userRepository.save(user);
            user.setUserId(userResponse.getUserId());
        }
        return check;
    }

    public Optional<User> getUserbyContact(User user) {
        Optional<User> check = userRepository.findByContact(user.getContact());
        return check;
    }
    public Optional<User> changePassword(UserModel user) {
        Optional<User> check = userRepository.findByContact(user.getContact());
        if(check.isPresent() &&
                PasswordEncryption.decrypt(check.get().getPassword()).equals(user.getPassword())){
            check.get().setPassword(PasswordEncryption.encrypt(user.getNewPassword()));
            userRepository.save(check.get());
            check.get().setPassword(user.getNewPassword());
            return check;
        }
        return Optional.ofNullable(null);
    }
}
