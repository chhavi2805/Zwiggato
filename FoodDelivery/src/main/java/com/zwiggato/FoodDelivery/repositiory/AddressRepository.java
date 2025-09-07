package com.zwiggato.FoodDelivery.repositiory;

import com.zwiggato.FoodDelivery.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

    List<Address> findAddByUserUserId(String userId);
}
