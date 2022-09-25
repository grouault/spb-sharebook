package com.udemy.sharebook.borrow;

import com.udemy.sharebook.book.Book;
import com.udemy.sharebook.user.UserInfo;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private UserInfo borrower; // emprunteur

    @ManyToOne
    private UserInfo lender; // preteur

    @ManyToOne
    private Book book;

    private LocalDate askDate;
    private LocalDate closeDate;

    public UserInfo getBorrower() {
        return borrower;
    }

    public Borrow setBorrower(UserInfo borrower) {
        this.borrower = borrower;
        return this;
    }

    public UserInfo getLender() {
        return lender;
    }

    public Borrow setLender(UserInfo lender) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCloseDate() {
        return closeDate;
    }

    public Borrow setCloseDate(LocalDate closeDate) {
        this.closeDate = closeDate;
        return this;
    }

}
