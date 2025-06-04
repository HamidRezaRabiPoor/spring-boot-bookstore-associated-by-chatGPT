package com.example.bookStorApi;

import com.example.bookStorApi.constants.Category;
import com.example.bookStorApi.dto.BookDTO;
import com.example.bookStorApi.service.BookServiceRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookStoApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(BookStoApiApplication.class, args);

	}

}
