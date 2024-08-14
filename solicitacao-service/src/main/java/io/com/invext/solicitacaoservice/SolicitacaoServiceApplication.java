package io.com.invext.solicitacaoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SolicitacaoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolicitacaoServiceApplication.class, args);
	}

}
