package com.eum602.security.basicAuth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration //Configuration class
public class ProjectConfig {
    //By adding this configuration the default password is not generated anymore
    @Bean //Inject the returned value as a bean in the spring context
    public UserDetailsService userDetailsService(){
        UserDetailsService userDetailsService = new InMemoryUserDetailsManager();
        return userDetailsService;
    }
}
