package com.steefy.bookstore.backend.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.steefy.bookstore.entity.Category;
import com.steefy.bookstore.repository.CategoryRepository;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CategoryRepositoryTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void CategoryRepository_Save() {
        
        Category category = Category.builder()
        .name("testName")
        .build();

        Category savedCategory = categoryRepository.save(category);

        Assertions.assertThat(savedCategory).isNotNull();
        Assertions.assertThat(savedCategory.getId()).isGreaterThan(0);
        Assertions.assertThat(savedCategory.getName()).isEqualTo("testName");
    }

    @Test
    public void CategoryRepository_GetById() {

        Category category = Category.builder()
        .name("testName")
        .build();

        categoryRepository.save(category);
        Category savedCategory = categoryRepository.findById(category.getId()).get();

        Assertions.assertThat(savedCategory).isNotNull();
        Assertions.assertThat(savedCategory.getId()).isGreaterThan(0);
        Assertions.assertThat(savedCategory.getName()).isEqualTo("testName");
    }

    @Test
    public void CategoryRepository_GetAll() {

        Category category1 = Category.builder()
        .name("testName1")
        .build();

        Category category2 = Category.builder()
        .name("testName2")
        .build();

        categoryRepository.save(category1);
        categoryRepository.save(category2);

        List<Category> categories = categoryRepository.findAll();

        Assertions.assertThat(categories).isNotNull();
        Assertions.assertThat(categories.size()).isEqualTo(2);

        Assertions.assertThat(categories.get(0).getId()).isGreaterThan(0);
        Assertions.assertThat(categories.get(0).getName()).isEqualTo("testName1");

        Assertions.assertThat(categories.get(1).getId()).isGreaterThan(0);
        Assertions.assertThat(categories.get(1).getName()).isEqualTo("testName2");
    }

    @Test
    public void CategoryRepository_Update() {

        Category category = Category.builder()
        .name("testName")
        .build();

        categoryRepository.save(category);

        Category savedCategory = categoryRepository.findById(category.getId()).get();
        savedCategory.setName("updatedTestName");

        Category updatedCategory = categoryRepository.save(savedCategory);

        Assertions.assertThat(updatedCategory).isNotNull();
        Assertions.assertThat(updatedCategory.getId()).isGreaterThan(0);
        Assertions.assertThat(updatedCategory.getName()).isEqualTo("updatedTestName");
    }

    @Test
    public void CategoryRepository_Delete() {

        Category category = Category.builder()
        .name("testName")
        .build();

        categoryRepository.save(category);
        categoryRepository.deleteById(category.getId());

        Optional<Category> deletedCategory = categoryRepository.findById(category.getId());

        Assertions.assertThat(deletedCategory).isEmpty();
    }
}
