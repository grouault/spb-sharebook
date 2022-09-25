package com.udemy.sharebook.book;

import com.udemy.sharebook.borrow.Borrow;
import com.udemy.sharebook.borrow.BorrowRepository;
import com.udemy.sharebook.user.UserInfo;
import com.udemy.sharebook.user.UserRepository;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BorrowRepository borrowRepository;

    @GetMapping(value="/books")
    public ResponseEntity<List<Book>> listBooks(@RequestParam(required = false) BookStatus status) {
        Integer userConnectedId = BookController.getUserConnectedId();
        List<Book> books;
        if (status != null && status == BookStatus.FREE) {
            books = bookRepository.findByStatusAndUserIdNotAndDeletedFalse(status, userConnectedId);
        } else {
            books = bookRepository.findByUserIdAndDeletedFalse(userConnectedId);
        }
        return ResponseEntity.ok(books);
        // return new ResponseEntity(Arrays.asList(book), HttpStatus.OK);
    }

    @PostMapping (value="/books")
    public ResponseEntity addBook(@Valid @RequestBody  Book book){
        Integer userConnectedId = BookController.getUserConnectedId();
        Optional<UserInfo> user = userRepository.findById(userConnectedId);

        Optional<Category> category = categoryRepository.findById(book.getCategoryId());
        if (user.isPresent()) {
            book.setUser(user.get());
        } else {
            return new ResponseEntity<>("you must provide a valid user", HttpStatus.BAD_REQUEST);
        }

        if (category.isPresent()) {
            book.setCategory(category.get());
        } else {
            return new ResponseEntity("you must provide a valid category", HttpStatus.BAD_REQUEST);
        }

        book.setDeleted(false);
        book.setStatus(BookStatus.FREE);
        Book createdBook = bookRepository.save(book);

        // TODO: a utiliser
        // return ResponseEntity.created(URI.create("/book/" + book.getId())).body(book).build();
        return new ResponseEntity(book, HttpStatus.CREATED);
    }

    @PutMapping(value="/books/bookId")
    public ResponseEntity updateBook(
            @PathVariable("bookId") String bookId,
            @Valid @RequestBody Book book){
        Optional<Book> bookToUpdate = bookRepository.findById(Integer.valueOf(bookId));
        if (!bookToUpdate.isPresent()) {
            return new ResponseEntity("Book not existing", HttpStatus.BAD_REQUEST);
        }

        Book bookToSave = bookToUpdate.get();
        Optional<Category> newCategory = categoryRepository.findById(book.getCategoryId());
        if (newCategory.isPresent()){
            bookToSave.setCategory(newCategory.get());
        }
        bookToSave.setTitle(book.getTitle());
        bookRepository.save(bookToSave);
        return new ResponseEntity(bookToSave, HttpStatus.OK);
    }

    @DeleteMapping(value="/books/{bookId}")
    public ResponseEntity deleteBook(@PathVariable("bookId") Integer bookId){
        Optional<Book> book = bookRepository.findById(bookId);
        if (!book.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        List<Borrow> borrows = borrowRepository.findByBookId(bookId);
        for (Borrow borrow:borrows) {
            if (borrow.getCloseDate() == null) {
                return new ResponseEntity(borrow.getBorrower(), HttpStatus.CONFLICT);
            }
        }
        Book bookToDelete = book.get();
        bookToDelete.setDeleted(true);
        bookRepository.save(bookToDelete);
        return ResponseEntity.noContent().build();
    }


    @GetMapping(value = "/books/{bookId}")
    public ResponseEntity getBook(@PathVariable String bookId){
        Optional<Book> bookToGet = bookRepository.findById(Integer.valueOf(bookId));
        if (!bookToGet.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(bookToGet.get(), HttpStatus.OK);
    }

    @GetMapping(value="/books/categories")
    public ResponseEntity listCategories(){
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            return new ResponseEntity("Aucune catégorie", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(categories, HttpStatus.OK);
    }

    public static Integer getUserConnectedId() {
        return 1;
    }

}
