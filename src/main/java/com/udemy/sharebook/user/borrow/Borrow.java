package com.udemy.sharebook.user.borrow;

import com.udemy.sharebook.book.Book;
import com.udemy.sharebook.user.User;
import java.time.LocalDate;

public class Borrow {

    private User borrower; // emprunteur
    private User lender; // preteur
    private Book book;

    private LocalDate askDate;
    private LocalDate closeDate;

    public User getBorrower() {
        return borrower;
    }

    public Borrow setBorrower(User borrower) {
        this.borrower = borrower;
        return this;
    }

    public User getLender() {
        return lender;
    }

    public Borrow setLender(User lender) {
        this.lender = lender;
        return this;
    }

    public Book getBook() {
        return book;
    }

    public Borrow setBook(Book book) {
        this.book = book;
        return this;
    }

    public LocalDate getAskDate() {
        return askDate;
    }

    public Borrow setAskDate(LocalDate askDate) {
        this.askDate = askDate;
        return this;
    }

    public LocalDate getCloseDate() {
        return closeDate;
    }

    public Borrow setCloseDate(LocalDate closeDate) {
        this.closeDate = closeDate;
        return this;
    }

}
