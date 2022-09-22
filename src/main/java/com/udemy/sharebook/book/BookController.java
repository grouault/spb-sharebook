package com.udemy.sharebook.book;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @GetMapping(value="/books")
    public ResponseEntity<List<Book>> listBooks(){
        Book book = new Book().setTitle("Tintin au Tibet").setCategory(new Category("BD"));
        return ResponseEntity.ok(Arrays.asList(book));
        // return new ResponseEntity(Arrays.asList(book), HttpStatus.OK);
    }

    @PostMapping (value="/books")
    public ResponseEntity addBook(@Valid @RequestBody  Book book){
        // TODO: persist

        // TODO: a utiliser
        // return ResponseEntity.created(URI.create("/book/" + book.getId())).body(book).build();
        return new ResponseEntity(book, HttpStatus.CREATED);
    }

    @PutMapping(value="/books/bookId")
    public ResponseEntity updateBook(
            @PathVariable("bookId") String bookId,
            @Valid @RequestBody Book book){
        // TODO: delete
        return new ResponseEntity(book, HttpStatus.OK);
    }

    @DeleteMapping(value="/books/{bookId}")
    public ResponseEntity deleteBook(@PathVariable("bookId") String bookId){
        // TODO: delete
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value="/books/categories")
    public ResponseEntity listCategories(){
        Category cat1 = new Category("BD");
        Category cat2 = new Category("Roman");
        return new ResponseEntity(Arrays.asList(cat1, cat2), HttpStatus.OK);
    }

}
