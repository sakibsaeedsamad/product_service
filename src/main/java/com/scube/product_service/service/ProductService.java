package com.scube.product_service.service;

import com.scube.product_service.payload.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(long categoryId, ProductDto productDto);

    List<ProductDto> getAllProductByCategoryId(long categoryId);


    List<ProductDto> getAllProduct();

    ProductDto getProductById(long categoryId, long productId);

    ProductDto updateProduct(long categoryId,long productId,ProductDto productDto);

    void deleteProduct(long categoryId,long productId);

}
