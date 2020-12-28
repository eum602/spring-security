package com.eum602.security.basicAuth;

import com.eum602.security.basicAuth.DAO.Kayak;
import com.eum602.security.basicAuth.jpa.KayakRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// This tells Hibernate to make a table out of this class
	@Bean
	ApplicationRunner init(KayakRepository repository) {

		String[][] data = {
				{"sea", "Andrew", "300.12", "NDK"},
				{"creek", "Andrew", "100.75", "Piranha"},
				{"loaner", "Andrew", "75", "Necky"}
		};

		return args -> {
			Stream.of(data).forEach(array -> {
				try {
					Kayak kayak = new Kayak(
							array[0],
							array[1],
							NumberFormat.getInstance().parse(array[2]),
							array[3]
					);
					repository.save(kayak);
				}
				catch ( ParseException e) {
					e.printStackTrace();
				}
			});
			repository.findAll().forEach(System.out::println);
		};
	}

}
