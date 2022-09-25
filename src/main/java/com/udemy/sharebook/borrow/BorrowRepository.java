package com.udemy.sharebook.borrow;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Integer> {

    List<Borrow> findByBorrowerId(Integer borrowerId);

    List<Borrow> findByBookId(Integer bookId);

}
