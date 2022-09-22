package com.udemy.sharebook.book;

import javax.validation.constraints.NotBlank;

public class Book {

    @NotBlank
    private String title;

    private Category category;

    private BookStatus status;

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Book setCategory(Category category) {
        this.category = category;
        return this;
    }

    public BookStatus getStatus() {
        return status;
    }

    public Book setStatus(BookStatus status) {
        this.status = status;
        return this;
    }

}
