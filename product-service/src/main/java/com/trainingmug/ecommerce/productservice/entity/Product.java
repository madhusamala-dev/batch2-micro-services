package com.trainingmug.ecommerce.productservice.entity;

import com.trainingmug.ecommerce.productservice.enums.Category;
import com.trainingmug.ecommerce.productservice.enums.Status;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "products")
@Data
public class Product {

    @Id
    private String id;
    private String name;
    private int maxRetailPrice;
    private byte discountPercentage;
    private float rating;
    private int reviewsCount;
    private Category category;
    private String company;
    private Status status;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
