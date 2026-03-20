package com.bootcamp.mvp_m6.service;

import com.bootcamp.mvp_m6.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User customer = userService.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+customer.getRole().name());

        return org.springframework.security.core.userdetails.User.builder()
                .username(customer.getEmail())
                .password(customer.getPassHash())
                .authorities(Collections.singletonList(authority))
                .build();
    }
}
