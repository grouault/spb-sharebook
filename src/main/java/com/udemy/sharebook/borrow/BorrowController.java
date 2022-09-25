package com.udemy.sharebook.borrow;

import com.udemy.sharebook.book.Book;
import com.udemy.sharebook.book.BookController;
import com.udemy.sharebook.book.BookRepository;
import com.udemy.sharebook.book.BookStatus;
import com.udemy.sharebook.book.CategoryRepository;
import com.udemy.sharebook.user.UserInfo;
import com.udemy.sharebook.user.UserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BorrowController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BorrowRepository borrowRepository;


    @GetMapping(value="/borrows")
    public ResponseEntity getMyBorrows(){
        List<Borrow> borrows = borrowRepository.findByBorrowerId(BookController.getUserConnectedId());
        return new ResponseEntity(borrows, HttpStatus.OK);
    }

    @PostMapping(value="/borrows/{bookId}")
    public ResponseEntity createBorrow(@PathVariable("bookId") String bookId){

        final Optional<UserInfo> borrower = userRepository.findById(BookController.getUserConnectedId());

        final Optional<Book> bookToBorrow = bookRepository.findById(Integer.valueOf(bookId));
        if (!bookToBorrow.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Book bookEntity = bookToBorrow.get();
        if (bookEntity.getStatus().equals(BookStatus.FREE)) {
            Borrow borrowToCreate = new Borrow();
            borrowToCreate.setBook(bookEntity);
            borrowToCreate.setLender(bookEntity.getUser());
            borrowToCreate.setBorrower(borrower.get());
            borrowToCreate.setAskDate(LocalDate.now());
            borrowRepository.save(borrowToCreate);
            bookEntity.setStatus(BookStatus.BORROWED);
            bookRepository.save(bookEntity);
        } else {
            return new ResponseEntity("Le livre est en cours d'emprunt", HttpStatus.CONFLICT);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping(value="/borrows/{borrowId}")
    public ResponseEntity deleteBorrow(@PathVariable("borrowId") String borrowId){

        Optional<Borrow> borrow = borrowRepository.findById(Integer.valueOf(borrowId));
        if (borrow.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // TODO: ces opérations sont a rendre atomique.
        Borrow borrowEntity = borrow.get();
        borrowEntity.setCloseDate(LocalDate.now());
        borrowRepository.save(borrowEntity);

        Book book = borrowEntity.getBook();
        book.setStatus(BookStatus.FREE);
        bookRepository.save(book);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }


}
