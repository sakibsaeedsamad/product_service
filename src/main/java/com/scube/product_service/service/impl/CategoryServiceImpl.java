package com.scube.product_service.service.impl;

import com.scube.product_service.entity.Category;
import com.scube.product_service.exception.ResourceNotFoundException;
import com.scube.product_service.payload.CategoryDto;
import com.scube.product_service.repository.CategoryRepository;
import com.scube.product_service.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        //convert dto to entity
        Category category = mapToEntity(categoryDto);
        Category newCategory = categoryRepository.save(category);

        //convert Entity to dto
        CategoryDto newCategoryDto = mapToCategoryDto(newCategory);

        log.info("Inside saveCategory of CategoryService");

        return newCategoryDto;
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        log.info("Inside getAllCategory of CategoryService");
        return  categoryList.stream().map(category -> mapToCategoryDto(category)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto findCategoryById(long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));

        log.info("Inside findByCategoryId of CategoryService");
        return mapToCategoryDto(category);
    }

    @Override
    public CategoryDto updateCategoryById(long categoryId, CategoryDto categoryDto) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));

        category.setCategoryName(categoryDto.getCategoryName());

        Category updatedCategory = categoryRepository.save(category);

        log.info("Inside updateByCategoryId of CategoryService");

        return mapToCategoryDto(updatedCategory);
    }

    @Override
    public void deleteCategoryById(long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));

        log.info("Inside deleteByCategoryId of CategoryService");

        categoryRepository.delete(category);
    }


    private CategoryDto mapToCategoryDto(Category category){;
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryName(category.getCategoryName());

        return categoryDto;
    }

    private Category mapToEntity(CategoryDto categoryDto){
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryName(categoryDto.getCategoryName());
        return category;
    }
}
