package com.example.bookStorApi.service.book;

import com.example.bookStorApi.ai.ChatgptServiceAssistance;
import com.example.bookStorApi.domain.Book;
import com.example.bookStorApi.dto.BookDTO;
import com.example.bookStorApi.exceptions.BookNotFoundException;
import com.example.bookStorApi.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceRepositoryImpl implements BookServiceRepository {


    private final ChatgptServiceAssistance chatgptServiceAssistance;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    BookRepository bookRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceRepositoryImpl.class);

    // constructor to initial ChatGPT class and repository
    public BookServiceRepositoryImpl(BookRepository bookRepository,
             ChatgptServiceAssistance chatgptServiceAssistance){
        this.bookRepository = bookRepository;
        this.chatgptServiceAssistance = chatgptServiceAssistance;
    }


    @Override
    public List<BookDTO> getAllBooks() {
        return mapBookListToBookDTOList(bookRepository.findAll());
    }

    @Override
    public Optional<BookDTO> getBookById(long id) {
        Optional<Book> bookById = Optional.ofNullable(bookRepository.findById(id));
        if(bookById.isEmpty())
            LOGGER.info("no such book with this id : {} exists", id);
        return Optional.ofNullable(modelMapper.map(bookById, BookDTO.class));
    }

    @Override
    public BookDTO getBookByAuthor(String author) {
        Optional<Book> bookByAuthor = Optional.ofNullable(bookRepository.findByAuthor(author));
        if(bookByAuthor.isEmpty())
            LOGGER.info("no book by this author {} exists", author);
        return modelMapper.map(bookByAuthor, BookDTO.class);
    }

    @Override
    public BookDTO getBookByTitle(String title) {
        Optional<Book> bookByTitle = Optional.ofNullable(bookRepository.findByTitle(title));
        if(bookByTitle.isEmpty())
            LOGGER.info("no such book by name of {} exists", title);
        return modelMapper.map(bookByTitle, BookDTO.class);
    }

    @Override
    public Void updateSoldBook(long id) {
      Optional<Book> bookById = Optional.ofNullable(Optional.ofNullable(bookRepository.findById(id))
              .orElseThrow(() -> new BookNotFoundException("no such book exists")));
      int totalCount = bookById.orElseThrow().getTotalCount() - 1;
      int sold = bookById.orElseThrow().getSold()  +1;
      bookRepository.updateTotalCountAndSoldById(id, totalCount, sold);
        return null;
    }

    @Override
    public void addBookQuantity(long id, int quantity) {
        Optional<Book> bookById = Optional.ofNullable(Optional.ofNullable(bookRepository.findById(id))
                .orElseThrow(() -> new BookNotFoundException("no such book exists")));
        int updatedValue = bookById.orElseThrow().getTotalCount() + quantity;
        bookRepository.addTotalCountById(id, updatedValue);

    }

    @Override
    public void addNewBook(BookDTO bookDTO)  {
        if(!bookDTO.objectIsEmpty()) {
            String prompt = """
                    in less than 30 words tell me what the book %s written by %s is about?
                    """.formatted(bookDTO.getTitle(), bookDTO.getAuthor());
            bookDTO.setGptRecommend(chatgptServiceAssistance.chat(prompt));
            bookRepository.save(modelMapper.map(bookDTO, Book.class));
        }else {
            LOGGER.info("object is empty");
        }
    }

    @Override
    public int bookTotalCount(long id) {
        Optional<Book> bookById = Optional.ofNullable(Optional.ofNullable(bookRepository.findById(id))
                .orElseThrow(() -> new BookNotFoundException("no book exists")));
        return bookById.orElseThrow().getTotalCount();
    }


    // map booklist to bookDTO list
    private List<BookDTO> mapBookListToBookDTOList(List<Book> books){
        return  books
                .stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }


}
