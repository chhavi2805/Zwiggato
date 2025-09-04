package com.zwiggato.FoodDelivery.repositiory;

import com.zwiggato.FoodDelivery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByContact(String contact);
}
