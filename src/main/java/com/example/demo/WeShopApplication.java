package com.example.demo;

import com.example.demo.entities.Category;
import com.example.demo.entities.Product;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.ICategoryService;
import com.example.demo.services.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class WeShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeShopApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner (
			ProductService productService ,
			CategoryRepository categoryRepository
	){
		return start->{


		};
	}
}
