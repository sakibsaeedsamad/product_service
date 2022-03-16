package com.scube.product_service.service;

import com.scube.product_service.payload.CategoryDto;
import com.scube.product_service.payload.ProductDto;

import java.util.ArrayList;
import java.util.List;

public interface CategoryService {

    CategoryDto saveCategory(CategoryDto categoryDto);

    ArrayList<CategoryDto> saveMultipleCategory( ArrayList<CategoryDto> categoryDto);

    List<CategoryDto> getAllCategory();

    CategoryDto findCategoryById(long categoryId);

    CategoryDto updateCategoryById(long categoryId, CategoryDto categoryDto);

    void deleteCategoryById(long categoryId);

}
