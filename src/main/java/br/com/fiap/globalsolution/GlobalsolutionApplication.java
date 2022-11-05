package br.com.fiap.globalsolution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GlobalsolutionApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlobalsolutionApplication.class, args);
	}

}
