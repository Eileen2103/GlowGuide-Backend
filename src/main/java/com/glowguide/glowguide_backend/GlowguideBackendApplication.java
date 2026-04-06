package com.glowguide.glowguide_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "models") // Modellerin olduğu paketi tanıtıyoruz
@EnableJpaRepositories(basePackages = "repository") // Eğer Repository'lerin de dışarıdaysa bunu ekle
public class GlowguideBackendApplication {

	
	
	public static void main(String[] args) {
		SpringApplication.run(GlowguideBackendApplication.class, args);
	}

}
