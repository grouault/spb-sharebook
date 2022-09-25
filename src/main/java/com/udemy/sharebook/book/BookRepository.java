package com.udemy.sharebook.book;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    // livre qui n'appartienne pas à l'utilisateur connecté et on delete
    List<Book> findByStatusAndUserIdNotAndDeletedFalse(BookStatus status, Integer user);

    // my-books
    List<Book> findByUserIdAndDeletedFalse(Integer user);

}
