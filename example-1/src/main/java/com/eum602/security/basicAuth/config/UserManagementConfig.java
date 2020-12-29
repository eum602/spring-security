package com.eum602.security.basicAuth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class UserManagementConfig {//Adds only beans responsible for managing the user
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        String usersByUserameQuery = "select username, password, enabled from users where username = ?"; //requires the three values in order to work properly
        String authsByUserQuery = "select username, authority from users where username = ?";
        JdbcUserDetailsManager userDetailsManager= new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery(usersByUserameQuery);
        userDetailsManager.setAuthoritiesByUsernameQuery(authsByUserQuery);
        return userDetailsManager;
        /*UserDetails user1 = new SingleStudentUserDetails("eum602","password","read");
        UserDetails user2 = new SingleStudentUserDetails("r2d2","password","write");
        List<UserDetails> users = List.of(user1,user2);
        return new InMemoryUserDetailsService(users);*/
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}