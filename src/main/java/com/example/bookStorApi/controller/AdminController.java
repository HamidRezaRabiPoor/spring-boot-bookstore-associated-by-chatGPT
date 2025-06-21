package com.example.bookStorApi.controller;

import com.example.bookStorApi.repository.UserRepository;
import com.example.bookStorApi.service.book.BookServiceRepository;
import com.example.bookStorApi.service.user.UserServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    // Inject required dependency
   private final BookServiceRepository bookServiceRepository;
   private final UserServiceRepository userServiceRepository;

   //Initial repositories within controller
    public AdminController(BookServiceRepository bookServiceRepository,
                           UserServiceRepository userServiceRepository){
        this.bookServiceRepository = bookServiceRepository;
        this.userServiceRepository = userServiceRepository;
    }


    // Add to the quantity of the book
    // Just administrator can update the number of the book
    @PutMapping("/add_book_quantity")
    public String addQuantityOfBook(long id, int quantity){
        bookServiceRepository.addBookQuantity(id, quantity);
        return "Quantity of the book increased";
    }

    // Delete book by selecting it id
    @PutMapping("/delete_book")
    public String deleteNoExistedBook(@RequestParam long id){
        bookServiceRepository.deleteUnexistedBook(id);
    return "Request to delete book has sent";
    }

    // Delete user By it id
    @PutMapping("/delete/user")
    public String deleteUserByItId(@RequestParam long id){
        userServiceRepository.deleteUserByUserId(id);
        return "Request to delete user has sent";
    }

}
