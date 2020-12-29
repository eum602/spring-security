package com.eum602.security.basicAuth;

import com.eum602.security.basicAuth.DAO.User;
import com.eum602.security.basicAuth.jpa.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// This tells Hibernate to make a table out of this class
	@Bean
	ApplicationRunner init(UserRepository repository){
		String[][] data = {
				{"eum602","password","read","1"},
				{"r2d2","password","write","1"},
				{"john","12345","write","1"}
		};
		return args -> {
			Stream.of(data).forEach(array->{
				try {
					User user = new User(array[0],array[1],array[2],Integer.parseInt(array[3]));
					repository.save(user);
				}catch (Exception e){
					e.printStackTrace();
				}
			});
			repository.findAll().forEach(System.out::println);
		};
	}
}
