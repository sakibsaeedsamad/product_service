package com.scube.product_service.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    public Long productId;
    public String productName;
    public String productDescription;
    public String productCreateDate;
}
