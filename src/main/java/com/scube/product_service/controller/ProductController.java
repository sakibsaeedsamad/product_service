package com.scube.product_service.controller;


import com.scube.product_service.payload.ProductDto;
import com.scube.product_service.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/category/{categoryId}/product")
    public ProductDto createProduct(@PathVariable("categoryId") Long categoryId,
                                    @RequestBody ProductDto productDto) {

        log.info("Inside the createProduct Controller");

        return productService.createProduct(categoryId, productDto);
    }

    @GetMapping("/category/{categoryId}/product")
    public List<ProductDto> getAllProductByCategoryId(@PathVariable("categoryId") Long categoryId) {

        log.info("Inside the getAllProductByCategoryId Controller");

        return productService.getAllProductByCategoryId(categoryId);
    }

    @GetMapping("/product")
    public List<ProductDto> getAllProduct() {

        log.info("Inside the getAllProduct Controller");

        return productService.getAllProduct();
    }

    @GetMapping("/category/{categoryId}/product/{productId}")
    public ProductDto getProductById(@PathVariable("categoryId") Long categoryId,
                                     @PathVariable("productId") Long productId) {

        log.info("Inside the getProductById Controller");

        return productService.getProductById(categoryId,productId);
    }

    @PutMapping("/category/{categoryId}/product/{productId}")
    public ProductDto updateProduct(@PathVariable("categoryId") Long categoryId,
                                                    @PathVariable("productId") Long productId,
                                                    @RequestBody ProductDto productDto) {

        log.info("Inside the updateProduct Controller");

        return productService.updateProduct(categoryId, productId, productDto);

    }



    @DeleteMapping("/category/{categoryId}/product/{productId}")
    public String deleteProduct(@PathVariable("categoryId") Long categoryId,
                                    @PathVariable("productId") Long productId) {

        log.info("Inside the deleteProduct Controller");

        productService.deleteProduct(categoryId, productId);
        return "Product Deleted Successfully";

    }





}
