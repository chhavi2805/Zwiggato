package com.zwiggato.FoodDelivery.utils;


import java.util.Base64;

public class PasswordEncryption {
    public static String encrypt(String password){
        return Base64.getEncoder().encodeToString(password.getBytes());
    }
    public static String decrypt(String password){
        return new String(Base64.getDecoder().decode(password));
    }
}
