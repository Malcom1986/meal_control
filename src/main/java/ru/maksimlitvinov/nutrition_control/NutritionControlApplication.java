package ru.maksimlitvinov.nutrition_control;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NutritionControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(NutritionControlApplication.class, args);
	}

}
