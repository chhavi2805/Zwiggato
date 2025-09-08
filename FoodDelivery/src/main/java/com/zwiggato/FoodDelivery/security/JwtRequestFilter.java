package com.zwiggato.FoodDelivery.security;

import com.zwiggato.FoodDelivery.model.User;
import com.zwiggato.FoodDelivery.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        String contact = null;
        String jwt = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            try {
                contact = jwtUtil.extractContact(jwt);
            } catch (Exception e) {
                // Invalid token, skip
            }
        }
        if (contact != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<User> userDetailsOpt = this.userService.getUserByContact(contact);
            if (userDetailsOpt.isPresent()) {
                User userDetails = userDetailsOpt.get();
                if (jwtUtil.validateToken(jwt, userDetails.getContact())) {
                    List<RolesGrantedAutorities> autorities = Arrays.stream(userDetails.getRole().split(","))
                            .map(x -> new RolesGrantedAutorities(x))
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList());

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, autorities);
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }else{
                response.sendError(401);
            }
        }
        filterChain.doFilter(request, response);
    }
}
