package com.scube.product_service.controller;


import com.scube.product_service.payload.ProductDto;
import com.scube.product_service.payload.ProductResponse;
import com.scube.product_service.service.ProductService;
import com.scube.product_service.utils.ProductPageConstraints;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class ProductController {

    @Autowired
    ProductService productService;


    @Operation(summary = "This is to save a Product in Db for a unique category.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Saved product form Db for a unique category.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Category not found with id : categoryId",
                    content = {@Content(mediaType = "application/json")}),
    })
    @PostMapping("/categories/{categoryId}/products")
    public ProductDto createProduct(@PathVariable("categoryId") long categoryId,
                                    @Valid @RequestBody ProductDto productDto) {

        log.info("Inside the createProduct Controller");

        return productService.createProduct(categoryId, productDto);
    }


    @Operation(summary = "This is to fetch All the Products stored in Db for a unique category.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched All the Products form Db for a unique category.",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/categories/{categoryId}/products")
    public List<ProductDto> getAllProductByCategoryId(@PathVariable("categoryId") long categoryId) {

        log.info("Inside the getAllProductByCategoryId Controller");

        return productService.getAllProductByCategoryId(categoryId);
    }

//    @GetMapping("/products")
//    public List<ProductDto> getAllProduct() {
//
//        log.info("Inside the getAllProduct Controller");
//
//        return productService.getAllProduct();
//    }

    @Operation(summary = "This is to fetch All the Products stored in Db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched a page of Products form Db.",
                    content = {@Content(mediaType = "application/json")})
    })
    //get All products rest api
    @GetMapping("/products")
    public ProductResponse getAllProduct(@RequestParam(value = "pageNo", defaultValue = ProductPageConstraints.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                         @RequestParam(value = "pageSize", defaultValue = ProductPageConstraints.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                         @RequestParam(value = "sortBy", defaultValue = ProductPageConstraints.DEFAULT_PAGE_SORT_BY, required = false) String sortBy,
                                         @RequestParam(value = "sortDir", defaultValue = ProductPageConstraints.DEFAULT_SORT_DIR, required = false) String sortDir) {
        log.info("Inside the getAllProduct Controller");

        return productService.getAllProduct(pageNo, pageSize, sortBy, sortDir);
    }

    @Operation(summary = "This is to fetch a unique product stored in Db for a unique Category.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched product form Db for a unique Category.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Product not found with id : productId",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "Product not from this Category",
                    content = {@Content(mediaType = "application/json")}),
    })
    @GetMapping("/categories/{categoryId}/products/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("categoryId") long categoryId,
                                                     @PathVariable("productId") long productId) {

        log.info("Inside the getProductById Controller");


        ProductDto productDtoResponse = productService.getProductById(categoryId, productId);

        return new ResponseEntity<>(productDtoResponse, HttpStatus.OK);
    }

    @Operation(summary = "This is to Update a unique product stored in Db for a unique Category.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Updated product form Db for a unique Category.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Product not found with id : productId",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "Product not from this Category",
                    content = {@Content(mediaType = "application/json")}),
    })

    @PutMapping("/categories/{categoryId}/products/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("categoryId") long categoryId,
                                                    @PathVariable("productId") long productId,
                                                    @Valid @RequestBody ProductDto productDto) {

        log.info("Inside the updateProduct Controller");

        ProductDto productDtoResponse = productService.updateProduct(categoryId, productId, productDto);

        return new ResponseEntity<>(productDtoResponse, HttpStatus.OK);

    }


    @Operation(summary = "This is to delete a unique product stored in Db for a unique Category.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Product deleted successfully.",
                    content = {@Content(mediaType = "text/plain;charset=UTF-8")}),
            @ApiResponse(responseCode = "404",
                    description = "Product not found with id : productId",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "Product not from this Category",
                    content = {@Content(mediaType = "application/json")}),
    })

    @DeleteMapping("/categories/{categoryId}/products/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("categoryId") long categoryId,
                                                @PathVariable("productId") Long productId) {

        log.info("Inside the deleteProduct Controller");

        productService.deleteProduct(categoryId, productId);

        return new ResponseEntity<>("Product Deleted Successfully", HttpStatus.OK);

    }


}
