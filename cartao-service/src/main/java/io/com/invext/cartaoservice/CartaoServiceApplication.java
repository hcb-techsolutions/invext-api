package io.com.invext.cartaoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CartaoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartaoServiceApplication.class, args);
	}

}
