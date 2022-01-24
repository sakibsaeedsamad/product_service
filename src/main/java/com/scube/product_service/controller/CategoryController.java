package com.scube.product_service.controller;

import com.scube.product_service.payload.CategoryDto;
import com.scube.product_service.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Slf4j
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public CategoryDto saveCategory(@RequestBody CategoryDto categoryDto) {
        log.info("Inside the saveCategory Controller");
        return categoryService.saveCategory(categoryDto);
    }

    @GetMapping
    public List<CategoryDto> getAllCategory() {
        log.info("Inside the getAllCategory Controller");
        return categoryService.getAllCategory();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findCategoryById(@PathVariable(name = "id") long categoryId) {
        log.info("Inside the findCategoryById Controller");
        CategoryDto categoryDtoResponse = categoryService.findCategoryById(categoryId);
        return new ResponseEntity<>(categoryDtoResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategoryById(@PathVariable(name = "id") long categoryId, @RequestBody CategoryDto categoryDto) {
        log.info("Inside the updateCategoryById Controller");
        CategoryDto categoryDtoResponse =  categoryService.updateCategoryById(categoryId, categoryDto);

        return new ResponseEntity<>(categoryDtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable(name = "id") long categoryId) {

        log.info("Inside the deleteCategoryById Controller");

        categoryService.deleteCategoryById(categoryId);

        return new ResponseEntity<>("Category Deleted Successfully", HttpStatus.OK);
    }


}
