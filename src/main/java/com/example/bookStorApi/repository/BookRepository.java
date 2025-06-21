package com.example.bookStorApi.repository;

import com.example.bookStorApi.domain.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {

    // find the book by it id
    @Query(value = "select * from book b where b.id = ?1", nativeQuery = true)
    Book findById(long id);

    // find the book by it title
    @Query(value = "select * from book b where b.title=?1", nativeQuery = true)
    Book findByTitle(String title);

    // find the book by the name of author
    @Query(value = "select * from book b where b.author = ?1", nativeQuery = true)
    Book findByAuthor(String author);

    // update columns by given arguments
    @Transactional
    @Modifying
    @Query(value = "update book b set b.totalCount=?2, b.sold=?3 where b.id=?1")
    void updateTotalCountAndSoldById(long id, int totalCount, int sold);

    // update total count of the book
    @Transactional
    @Modifying
    @Query(value = "update book b set b.totalCount=?2 where b.id=?1")
    void addTotalCountById(long id, int totalCount);

    // Delete no existed book from DB
    @Transactional
    @Modifying
    //@Query(value = "delete any from book b where b.id = ?1")
    void deleteBookById(long id);
}
