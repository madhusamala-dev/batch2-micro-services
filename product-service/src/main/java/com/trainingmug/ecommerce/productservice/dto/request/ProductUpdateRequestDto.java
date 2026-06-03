package com.trainingmug.ecommerce.productservice.dto.request;

import com.trainingmug.ecommerce.productservice.enums.Category;

public class ProductUpdateRequestDto {
    private String id;
    private String name;
    private int maxRetailPrice;
    private byte discountPercentage;
    private String brand;
    private Category category;
    private String company;
}
