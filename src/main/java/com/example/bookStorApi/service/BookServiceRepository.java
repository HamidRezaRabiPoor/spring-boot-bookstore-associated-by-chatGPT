package com.example.bookStorApi.service;

import com.example.bookStorApi.dto.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookServiceRepository {

    // return list of all books
    List<BookDTO> getAllBooks();

    // book by id
    Optional<BookDTO> getBookById(long id);

    // book by author
    BookDTO getBookByAuthor(String author);

    // book by title
    BookDTO getBookByTitle(String title);

    // update book
    void updateSoldBook(long id);

    // add quantity of book
    void addBookQuantity(long id, int quantity);

    // add new book
    void addNewBook(BookDTO bookDTO);

    // book total count
    int bookTotalCount(long id);
}
