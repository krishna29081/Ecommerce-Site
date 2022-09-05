package com.project.shopping;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShoppingAppApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingAppApisApplication.class, args);
	}

    @Bean
    ModelMapper modelmapper() {
        return new ModelMapper();
    }

}
