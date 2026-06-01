package com.glowguide.glowguide_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "models") // Modellerin olduğu paketi tanıt
@ComponentScan(basePackages = { "com.glowguide.glowguide_backend", // Ana paket
		"analysis", "ingredient", "ocr", "controller", "service", "repository", "models", "dto", "config" })
@EnableJpaRepositories(basePackages = "repository")
public class GlowguideBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlowguideBackendApplication.class, args);
	}

}
