package com.trainingmug.ecommerce.productservice.service;

import com.trainingmug.ecommerce.productservice.dto.request.ProductRequestDto;
import com.trainingmug.ecommerce.productservice.dto.request.ProductUpdateRequestDto;
import com.trainingmug.ecommerce.productservice.dto.response.CategoryCountDto;
import com.trainingmug.ecommerce.productservice.dto.response.CompanyCountDto;
import com.trainingmug.ecommerce.productservice.dto.response.ProductResponseDto;
import com.trainingmug.ecommerce.productservice.dto.response.StatusCountDto;
import com.trainingmug.ecommerce.productservice.enums.Category;
import com.trainingmug.ecommerce.productservice.enums.Status;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    ProductResponseDto save(ProductRequestDto requestDto);

    ProductResponseDto getById(String productId);
    List<ProductResponseDto> getAll();
    ProductResponseDto update(
            String productId,
            ProductUpdateRequestDto productUpdateRequestDto
    );
    void delete(String productId);
    Page<ProductResponseDto> getAllByPage(
            int page,
            int size,
            String sortBy
    );

    List<ProductResponseDto> getByCategory(
            Category category
    );

    List<ProductResponseDto> getByStatus(
            Status status
    );

    List<ProductResponseDto> getTopRatedProducts();


    List<ProductResponseDto> searchProducts(
            String keyword
    );

    List<CategoryCountDto> countProductsByCategory();

    List<CompanyCountDto> countProductsByCompany();

    List<StatusCountDto> countProductsByStatus();
}
