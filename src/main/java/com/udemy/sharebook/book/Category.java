package com.udemy.sharebook.book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String label;

    public Category() {
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
