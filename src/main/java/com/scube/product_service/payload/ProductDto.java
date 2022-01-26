package com.scube.product_service.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;


@Data
public class ProductDto {

    private long productId;

    @NotEmpty
    @Size(min = 5, message = "Product Name should have 5 characters.")
    private String productName;
    @NotEmpty
    @Size(min = 5, message = "Product Description should have 5 characters.")
    private String productDescription;

    private String productCreateDate;

    private Set<FeedbackDto> feedback;

}
