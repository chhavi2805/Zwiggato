package com.zwiggato.FoodDelivery;

import com.zwiggato.FoodDelivery.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FoodDeliveryApplication {

	public static void main(String[] args) {
//		User u = new User("Chhavi","Srivastava");
//		System.out.println(u.toString());
		SpringApplication.run(FoodDeliveryApplication.class, args);
	}

}
