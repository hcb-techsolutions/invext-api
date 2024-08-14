package io.com.invext.emprestimoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EmprestimoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmprestimoServiceApplication.class, args);
	}

}
