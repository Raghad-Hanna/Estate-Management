package com.raghad.estate;

import com.raghad.estate.utils.JWTUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableAspectJAutoProxy
@EnableCaching
public class EstateApplication {
	public static void main(String[] args) {
		SpringApplication.run(EstateApplication.class, args);
	}

	@Bean
	public JWTUtil getJwtUtil() {
		return new JWTUtil();
	}
}
