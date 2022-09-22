package com.udemy.sharebook.user.borrow;

import java.time.LocalDate;
import java.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BorrowController {

    @GetMapping(value="/borrows")
    public ResponseEntity getMyBorrows(){
        // TODO
        Borrow borrow = new Borrow();
        borrow.setAskDate(LocalDate.now());
        return new ResponseEntity(Arrays.asList(borrow), HttpStatus.OK);
    }

    @PostMapping(value="/borrows/{bookId}")
    public ResponseEntity createBorrow(@PathVariable("bookId") String bookId){
        // TODO
        // Borrow borrow = new Borrow();
        // borrow.setBorrower(borrower).setLender(lender).setBook(book);
        // borrow.setAskDate(LocalDate.now());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping(value="/borrows/{borrowId}")
    public ResponseEntity deleteBorrow(@PathVariable("borrowId") String borrowId){
        // TODO: delete
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
