package com.udemy.sharebook.repository;

import com.udemy.sharebook.book.Category;
import com.udemy.sharebook.book.CategoryRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        assertThat(categories).as("Les catégories n'ont pas été chargées correctement").hasSize(7);
    }

}
