package com.example.bookStorApi;




import com.example.bookStorApi.domain.Book;
import com.example.bookStorApi.repository.BookRepository;
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
