package com.scube.product_service.repository;

import com.scube.product_service.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {

        Category category =
                Category.builder()
                        .categoryId(1)
                .categoryName("Finance")
                .build();

        testEntityManager.persist(category);
    }

    @Test
    @DisplayName("Get category by categoryID")
    public void  whenFindById_ThenReturnCategory(){

        Category category = categoryRepository.findById(1L).get();

        assertEquals(category.getCategoryName(),"Finance");
    }
}