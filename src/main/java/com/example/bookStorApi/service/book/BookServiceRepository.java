package com.example.bookStorApi.service.book;

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
    Void updateSoldBook(long id);

    // add quantity of the book
    void addBookQuantity(long id, int quantity);

    // add the new book
    void addNewBook(BookDTO bookDTO) throws InterruptedException;

    // book total count
    int bookTotalCount(long id);
}
