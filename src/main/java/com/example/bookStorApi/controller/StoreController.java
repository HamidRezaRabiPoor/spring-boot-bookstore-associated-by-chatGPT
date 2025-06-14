package com.example.bookStorApi.controller;

import com.example.bookStorApi.dto.BookDTO;
import com.example.bookStorApi.service.book.BookServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/bookstore")
public class StoreController {
    @Autowired
    BookServiceRepository bookServiceRepository;


    // List of existed books
    @GetMapping("/books")
    public ResponseEntity<BookDTO> bookDtoList(){
        Optional<BookDTO> optionalBookDTO = Optional.ofNullable(
                (BookDTO) bookServiceRepository.getAllBooks());
        return optionalBookDTO.map(bookDTO ->
                new ResponseEntity<>(bookDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    // Add the new book to store
    @PostMapping("/registry")
   private String addNewUser(@RequestParam BookDTO bookDTO) throws InterruptedException {
        String msg;
        if (!bookDTO.objectIsEmpty()) {
            bookServiceRepository.addNewBook(bookDTO);
            msg = "new record added successfully";
        } else {
            msg = "there is a problem in adding new book";
        }
        return msg;
    }


}
