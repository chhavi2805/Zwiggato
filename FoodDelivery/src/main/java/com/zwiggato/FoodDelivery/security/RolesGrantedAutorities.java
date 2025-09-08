package com.zwiggato.FoodDelivery.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

public class RolesGrantedAutorities implements GrantedAuthority {

    private String authority;

    public RolesGrantedAutorities(String role){
        if(checkValidRole(role))
            this.authority = role;
    }

    public boolean checkValidRole(String role){
        try {
            Roles.valueOf(role);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
