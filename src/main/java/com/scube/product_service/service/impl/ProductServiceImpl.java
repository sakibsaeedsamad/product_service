package com.scube.product_service.service.impl;

import com.scube.product_service.entity.Category;
import com.scube.product_service.entity.Product;
import com.scube.product_service.exception.ProductServiceException;
import com.scube.product_service.exception.ResourceNotFoundException;
import com.scube.product_service.payload.ProductDto;
import com.scube.product_service.payload.ProductResponse;
import com.scube.product_service.repository.CategoryRepository;
import com.scube.product_service.repository.ProductRepository;
import com.scube.product_service.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime dateTime = LocalDateTime.now();

    @Override
    public ProductDto createProduct(long categoryId, ProductDto productDto) {
        Product product = mapToEntity(productDto);
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
        product.setCategory(category);


        product.setProductCreateDate(dateTime.toString());

        //save Product to db
        Product newProduct = productRepository.save(product);

        log.info("Inside createProduct of ProductService");

        return mapToProductDto(newProduct);
    }

    @Override
    public List<ProductDto> getAllProductByCategoryId(long categoryId) {
        //retrive products by categoryId
        List<Product> products = productRepository.findByCategoryId(categoryId);

        //convert list of product entities to list of product dto
        return products.stream().map(product ->
                mapToProductDto(product)).collect(Collectors.toList());
    }

//    @Override
//    public List<ProductDto> getAllProduct() {
//
//        List<Product> products = productRepository.findAll();
//
//        log.info("Inside getAllProduct of CategoryService");
//
//        return products.stream().map(product ->
//                mapToProductDto(product)).collect(Collectors.toList());
//    }

    @Override
    public ProductResponse getAllProduct(int pageNo, int pageSize, String sortBy, String sortDirection) {
        //extracting sort direction
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();

        //create pageable object
        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);
        Page<Product> products = productRepository.findAll(pageable);

        //get content of page object
        List<Product>productList = products.getContent();

        List<ProductDto>content= productList.stream().map(product -> mapToProductDto(product)).collect(Collectors.toList());
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(content);
        productResponse.setPageSize(products.getSize());
        productResponse.setTotalElements(products.getTotalElements());
        productResponse.setLast(products.isLast());
        productResponse.setTotalPages(products.getTotalPages());
        productResponse.setPageNo(products.getNumber());

        return productResponse;
    }

    @Override
    public ProductDto getProductById(long categoryId, long productId) {

        //retrive category entity by id
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                ()-> new ResourceNotFoundException("Category","id",categoryId));

        //retrive Product by id
        Product product= productRepository.findById(productId).orElseThrow(
                ()-> new ResourceNotFoundException("Product","id",productId));


        if(product.getCategory().getCategoryId() != (category.getCategoryId())){
            throw new ProductServiceException(HttpStatus.BAD_REQUEST,"Product does not belong to this Category");
        }

        log.info("Inside getProductById of ProductService");

        return  mapToProductDto(product);

    }

    @Override
    public ProductDto updateProduct(long categoryId, long productId, ProductDto productDto) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));

        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product","id",productId));

        if (product.getCategory().getCategoryId() != (category.getCategoryId())){
            throw new ProductServiceException(HttpStatus.BAD_REQUEST,"Product is not from this Category");
        }
        product.setProductName(productDto.getProductName());
        product.setProductDescription(productDto.getProductDescription());
        product.setProductCreateDate(dateTime.toString());

        Product updatedProduct = productRepository.save(product);

        log.info("Inside updateProduct of ProductService");

        return mapToProductDto(updatedProduct);
    }

    @Override
    public void deleteProduct(long categoryId, long productId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));

        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product","id",productId));

        if (product.getCategory().getCategoryId() != (category.getCategoryId())){
            throw new ProductServiceException(HttpStatus.BAD_REQUEST,"Product not from this Category");
        }

        log.info("Inside deleteProduct of ProductService");

        productRepository.delete(product);

    }


    public Product mapToEntity(ProductDto productDto){

        Product product = modelMapper.map(productDto,Product.class);

//        Product product = new Product();
//        product.setProductId(productDto.getProductId());
//        product.setProductName(productDto.getProductName());
//        product.setProductDescription(productDto.getProductDescription());
        return product;
    }
    public ProductDto mapToProductDto(Product product){
        ProductDto productDto = modelMapper.map(product,ProductDto.class);

//        ProductDto productDto = new ProductDto();
//        productDto.setProductId(product.getProductId());
//        productDto.setProductName(product.getProductName());
//        productDto.setProductDescription(product.getProductDescription());
//        productDto.setProductCreateDate(product.getProductCreateDate());
        return productDto;
    }


}
