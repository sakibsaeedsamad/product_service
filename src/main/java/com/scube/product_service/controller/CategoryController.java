package com.scube.product_service.controller;

import com.scube.product_service.payload.CategoryDto;
import com.scube.product_service.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@Slf4j
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public CategoryDto saveCategory(@RequestBody CategoryDto categoryDto){
        log.info("Inside the saveCategory Controller");
        return categoryService.saveCategory(categoryDto);
    }

    @GetMapping
    public List<CategoryDto> getAllCategory(){
        log.info("Inside the getAllCategory Controller");
        return  categoryService.getAllCategory();
    }

    @GetMapping("/{id}")
    public  CategoryDto findCategoryById(@PathVariable (value = "id") Long categoryId){

        log.info("Inside the findCategoryById Controller");

        return  categoryService.findCategoryById(categoryId);
    }

    @PutMapping("/{id}")
    public CategoryDto updateCategoryById(@PathVariable (value = "id") Long categoryId, @RequestBody CategoryDto categoryDto){
        log.info("Inside the updateCategoryById Controller");
        return categoryService.updateCategoryById(categoryId,categoryDto);
    }

    @DeleteMapping("/{id}")
    public String deleteCategoryById(@PathVariable (value = "id") Long categoryId ){

        log.info("Inside the deleteCategoryById Controller");

        categoryService.deleteCategoryById(categoryId);

        return "Category Deleted Successfully";
    }



}
