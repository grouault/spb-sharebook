package com.udemy.sharebook.book;

public class Category {

    private String label;

    public Category(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public Category setLabel(String label) {
        this.label = label;
        return this;
    }

}
