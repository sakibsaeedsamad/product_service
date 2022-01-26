package com.scube.product_service.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class CategoryDto {
    private long categoryId;

    @NotEmpty
    @Size(min = 5, message = "Category Name should have 5 characters.")
    private String categoryName;

    private Set<ProductDto> products;
}
