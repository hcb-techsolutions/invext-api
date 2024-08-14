package io.com.invext.outroassuntoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class OutroAssuntoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OutroAssuntoServiceApplication.class, args);
	}

}
