package com.zwiggato.FoodDelivery.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "my_secret_key";

    public String generateToken(String contact) {
        Map<String, Object> claims = new HashMap<>();
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(contact)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + 5 * 60 * 1000)) // 5 minutes
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractContact(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean validateToken(String token, String contact) {
        return extractContact(token).equals(contact) && !isTokenExpired(token);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}
