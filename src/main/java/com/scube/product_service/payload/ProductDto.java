package com.scube.product_service.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    public long productId;

    @NotEmpty
    @Size(min = 5, message = "Product Name should have 5 characters.")
    public String productName;
    @NotEmpty
    @Size(min = 5, message = "Product Description should have 5 characters.")
    public String productDescription;

    public String productCreateDate;

    private Set<FeedbackDto> feedbacks;
}
