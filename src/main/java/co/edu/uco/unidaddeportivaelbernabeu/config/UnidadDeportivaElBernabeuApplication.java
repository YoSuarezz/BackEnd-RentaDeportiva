package co.edu.uco.unidaddeportivaelbernabeu.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "co.edu.uco.unidaddeportivaelbernabeu.api.controller")
public class UnidadDeportivaElBernabeuApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnidadDeportivaElBernabeuApplication.class, args);
	}
}
