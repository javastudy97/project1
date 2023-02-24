package org.project.omwp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OmwpApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmwpApplication.class, args);
	}

}
