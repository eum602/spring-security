package com.eum602.security.basicAuth.config;

import com.eum602.security.basicAuth.config.single.SingleStudentUserDetails;
import com.eum602.security.basicAuth.services.inmemory.InMemoryUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class UserManagementConfig {//Adds only beans responsible for managing the user
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user1 = new SingleStudentUserDetails("eum602","password","user");
        UserDetails user2 = new SingleStudentUserDetails("r2d2","password","user");
        List<UserDetails> users = List.of(user1,user2);
        return new InMemoryUserDetailsService(users);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}