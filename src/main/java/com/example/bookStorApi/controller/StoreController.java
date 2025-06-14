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
    // returns a list of all existed books in database
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
    // book by title of the book
    // [ask ChatGPT to give some information about this book]
    @GetMapping("/book_by_title/{title}")
    public ResponseEntity<BookDTO> bookByTitle(@PathVariable("title") String title){
        Optional<BookDTO> optionalBookDTO = Optional.ofNullable(bookServiceRepository.getBookByTitle(title));
        return optionalBookDTO.map(bookDTO ->
                        new ResponseEntity<>(bookDTO, HttpStatus.OK))
                .orElseGet(() ->
                        new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
    // book by name of the author
    // [ask ChatGPT to give some information about this author]
   @GetMapping("/book_by_author/{author}")
   public ResponseEntity<BookDTO> bookDTOResponseEntity(@PathVariable("author") String author){
        Optional<BookDTO> optionalBookDTO = Optional.ofNullable(bookServiceRepository.getBookByAuthor(author));
        return optionalBookDTO.map(bookDTO ->
                        new ResponseEntity<>(bookDTO, HttpStatus.OK))
                .orElseGet(() ->
                        new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
   }
    // Get total count of the book
    @GetMapping("/total")
    public ResponseEntity<Integer> integerResponseEntity(@RequestParam(required = false) long id){
        Optional<Integer> optionalInteger = Optional.of(bookServiceRepository.bookTotalCount(id));
        return optionalInteger.map(integer ->
                        new ResponseEntity<>(integer, HttpStatus.OK))
                .orElseGet(() ->
                        new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
}
    // Updates the quantity of sold book
    // and decrease it number
    @PutMapping("/update_sold_book")
    private String updateBookAfterSold(@RequestParam long id){
        bookServiceRepository.updateSoldBook(id);
        return "Quantity of sold updated";
    }
    // Updates the quantity of book by adding it number
    // in increase per quantity parameter
    @PutMapping("/add_book_quantity")
    private String updateQuantityOfBook(@RequestParam long id, int quantity){
        bookServiceRepository.addBookQuantity(id, quantity);
        return "Quantity of book updated";
    }






}
