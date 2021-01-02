package com.eum602.security.basicAuth;

import com.eum602.security.basicAuth.DAO.User;
import com.eum602.security.basicAuth.jpa.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;

import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication {

	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		checkStandardEncryption();
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

	public static void checkStandardEncryption(){
		StringKeyGenerator keyGenerator = KeyGenerators.string();
		String salt = keyGenerator.generateKey();
		String password = "password";
		String valueToEncrypt = "Some confidential text";
		BytesEncryptor e = Encryptors.standard(password,salt);
		byte[] encrypted = e.encrypt(valueToEncrypt.getBytes()); //Uses CBC (cypher block chaining) -> not so secure
		byte[] decrypted = e.decrypt(encrypted);
		logger.info("Plain text: " + valueToEncrypt);
		logger.info("Salt: " + salt);
		logger.info("password: " + password);
		logger.info("Encrypted: " + encrypted);
		logger.info("Decrypted: " + decrypted);
		String decryptedString = new String(decrypted);
		logger.info("Decrypted plain text: " + decryptedString);
		logger.info("plain == Decrypted? " + decryptedString.equals(valueToEncrypt));
	}
}
