package com.example.bookStorApi.controller;

import com.example.bookStorApi.ai.ChatgptServiceAssistance;
import com.example.bookStorApi.dto.BookDTO;
import com.example.bookStorApi.service.book.BookServiceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookstore")
public class StoreController {

    // Initial repository and ChatGPT assistance
    private final BookServiceRepository bookServiceRepository;
    private final ChatgptServiceAssistance chatgptServiceAssistance;
    public StoreController(BookServiceRepository bookServiceRepository,
                           ChatgptServiceAssistance chatgptServiceAssistance){
        this.bookServiceRepository = bookServiceRepository;
        this.chatgptServiceAssistance = chatgptServiceAssistance;
    }


    // List of existed books
    // returns a list of all existed books in the database
    @GetMapping("/books")
    public ResponseEntity<List<BookDTO>> bookDtoList(){
        Optional<List<BookDTO>> optionalBookDTO = Optional.ofNullable(bookServiceRepository.getAllBooks());
        return optionalBookDTO.map(bookDTO ->
                 new ResponseEntity<>(bookDTO, HttpStatus.OK))
            .orElseGet(() ->
                 new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
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
    // *** when using @PathVariable in URL don't place it within double quotation ***
    @GetMapping("/book_by_title/{title}")
    public ResponseEntity<BookDTO> bookByTitle(@PathVariable("title") String title){
        Optional<BookDTO> optionalBookDTO = Optional.ofNullable(bookServiceRepository.getBookByTitle(title));
        // Declare prompt and prepare it for user
        String prompt = String.format(
                "Outline other similar books to %s", optionalBookDTO.orElseThrow().getTitle());
        optionalBookDTO.orElseThrow().setGptRecommend(chatgptServiceAssistance.chat(prompt));
        return optionalBookDTO.map(bookDTO ->
                        new ResponseEntity<>(bookDTO, HttpStatus.OK))
                .orElseGet(() ->
                        new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    // book by name of the author
    // [ask ChatGPT to give some information about this author]
    // *** when using @PathVariable in URL don't place it within double quotation ***
   @GetMapping("/book_by_author/{author}")
   public ResponseEntity<BookDTO> bookDTOResponseEntity(@PathVariable("author") String author){
        Optional<BookDTO> optionalBookDTO = Optional.ofNullable(bookServiceRepository.getBookByAuthor(author));
       // Declare prompt and prepare it for user
        String prompt = String.format(
                "Give me other books written by this author: %s", optionalBookDTO.orElseThrow().getAuthor());
        optionalBookDTO.orElseThrow().setGptRecommend(chatgptServiceAssistance.chat(prompt));
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



}
