package com.udemy.sharebook.user;

import javax.validation.constraints.Size;

public class User {

    public String email;

    @Size(min = 2, max = 25, message = "le nom doit faire entre 2 et 25 caractères")
    public String firstName;

    @Size(min = 2, max = 25, message = "le nom doit faire entre 2 et 25 caractères")
    public String lastName;

    public String password;

    public User() {
    }

    public User(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

}
