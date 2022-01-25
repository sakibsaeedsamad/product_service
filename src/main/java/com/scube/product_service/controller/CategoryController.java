package com.scube.product_service.controller;

import com.scube.product_service.payload.CategoryDto;
import com.scube.product_service.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "This is to save a Category in the Db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Saved Category from Db.",
                    content = {@Content(mediaType = "application/json")}),
    })
    @PostMapping
    public CategoryDto saveCategory(@RequestBody CategoryDto categoryDto) {
        log.info("Inside the saveCategory Controller");
        return categoryService.saveCategory(categoryDto);
    }


    @Operation(summary = "This is to fetch all Categories from Db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched all Categories from Db",
                    content = {@Content(mediaType = "application/json")}),
    })
    @GetMapping
    public List<CategoryDto> getAllCategory() {
        log.info("Inside the getAllCategory Controller");
        return categoryService.getAllCategory();
    }

    @Operation(summary = "This is to fetch unique Category stored in Db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched Category form Db.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Category not found with id : categoryId",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findCategoryById(@PathVariable(name = "id") long categoryId) {
        log.info("Inside the findCategoryById Controller");
        CategoryDto categoryDtoResponse = categoryService.findCategoryById(categoryId);
        return new ResponseEntity<>(categoryDtoResponse, HttpStatus.OK);
    }

    @Operation(summary = "This is to update a unique Category stored in Db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Updated Category form Db.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Category not found with id : categoryId",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategoryById(@PathVariable(name = "id") long categoryId, @RequestBody CategoryDto categoryDto) {
        log.info("Inside the updateCategoryById Controller");
        CategoryDto categoryDtoResponse =  categoryService.updateCategoryById(categoryId, categoryDto);

        return new ResponseEntity<>(categoryDtoResponse, HttpStatus.OK);
    }

    @Operation(summary = "This is to delete a unique Category stored in Db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Category Deleted Successfully form Db.",
                    content = {@Content(mediaType = "text/plain;charset=UTF-8")}),
            @ApiResponse(responseCode = "404",
                    description = "Category not found with id : categoryId",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable(name = "id") long categoryId) {

        log.info("Inside the deleteCategoryById Controller");

        categoryService.deleteCategoryById(categoryId);

        return new ResponseEntity<>("Category Deleted Successfully", HttpStatus.OK);
    }


}
