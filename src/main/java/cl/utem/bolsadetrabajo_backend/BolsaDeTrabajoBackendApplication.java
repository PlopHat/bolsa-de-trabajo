package cl.utem.bolsadetrabajo_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class BolsaDeTrabajoBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BolsaDeTrabajoBackendApplication.class, args);
	}

}
