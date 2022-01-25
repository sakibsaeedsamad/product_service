package com.scube.product_service.service;

import com.scube.product_service.entity.Category;
import com.scube.product_service.payload.CategoryDto;
import com.scube.product_service.repository.CategoryRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {

        Category category = Category.builder()
                .categoryId(1)
                .categoryName("Finance")
                .build();

        Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.ofNullable(category));
    }

    @Test
    @DisplayName("Get category by categoryID")
    @Disabled
    void findCategoryById() {

        long categoryId=1;
        CategoryDto found = categoryService.findCategoryById(categoryId);

        assertEquals(categoryId,found.getCategoryId());
    }


}