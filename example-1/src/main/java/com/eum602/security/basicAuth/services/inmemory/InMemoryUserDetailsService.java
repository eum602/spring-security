package com.eum602.security.basicAuth.services.inmemory;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class InMemoryUserDetailsService implements UserDetailsService {
    private final List<UserDetails> users;

    public InMemoryUserDetailsService(List<UserDetails> users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return users
                .stream()
                .filter(
                        u->u.getUsername().equals(s)
                )
                .findFirst()
                .orElseThrow(
                        ()-> new UsernameNotFoundException("Username not found")
                );
    }
}