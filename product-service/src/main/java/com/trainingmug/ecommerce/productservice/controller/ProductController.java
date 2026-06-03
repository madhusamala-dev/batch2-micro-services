package com.trainingmug.ecommerce.productservice.controller;

import com.trainingmug.ecommerce.productservice.dto.request.ProductRequestDto;
import com.trainingmug.ecommerce.productservice.dto.request.ProductUpdateRequestDto;
import com.trainingmug.ecommerce.productservice.dto.response.CategoryCountDto;
import com.trainingmug.ecommerce.productservice.dto.response.CompanyCountDto;
import com.trainingmug.ecommerce.productservice.dto.response.ProductResponseDto;
import com.trainingmug.ecommerce.productservice.dto.response.StatusCountDto;
import com.trainingmug.ecommerce.productservice.enums.Category;
import com.trainingmug.ecommerce.productservice.enums.Status;
import com.trainingmug.ecommerce.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

     /*
     -------------------------------------------------------
                    CREATE PRODUCT
     -------------------------------------------------------
     */

    @PostMapping
    public ResponseEntity<ProductResponseDto> save(
            @RequestBody ProductRequestDto requestDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(requestDto));
    }

    /*
     -------------------------------------------------------
                    GET PRODUCT BY ID
     -------------------------------------------------------
     */

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> getProductById(
            @PathVariable String productId) {
        return ResponseEntity.ok(productService.getById(productId));
    }

    /*
     -------------------------------------------------------
                    GET ALL PRODUCTS
     -------------------------------------------------------
     */

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    /*
     -------------------------------------------------------
                    UPDATE PRODUCT
     -------------------------------------------------------
     */

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> update(
            @PathVariable String productId,
            @RequestBody ProductUpdateRequestDto requestDto) {

        return ResponseEntity.ok(productService.update(
                productId,
                requestDto
        ));
    }

    /*
     -------------------------------------------------------
                    DELETE PRODUCT
     -------------------------------------------------------
     */

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> delete(@PathVariable String productId) {
        productService.delete(productId);
        return ResponseEntity.ok().build();
    }

    /*
     -------------------------------------------------------
                    PAGINATION & SORTING
     -------------------------------------------------------
     */

    @GetMapping("/pagination")
    public ResponseEntity<Page<ProductResponseDto>> getAllByPage(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy) {

        return ResponseEntity.ok(productService.getAllByPage(
                page,
                size,
                sortBy
        ));
    }

    /*
     -------------------------------------------------------
                    FILTER BY CATEGORY
     -------------------------------------------------------
     */

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductResponseDto>> getProductsByCategory(
            @PathVariable Category category) {
        return ResponseEntity.ok(productService.getByCategory(category));
    }

    /*
     -------------------------------------------------------
                    FILTER BY STATUS
     -------------------------------------------------------
     */

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ProductResponseDto>> getProductsByStatus(
            @PathVariable Status status) {

        return ResponseEntity.ok(productService.getByStatus(status));
    }

    /*
     -------------------------------------------------------
                    TOP RATED PRODUCTS
     -------------------------------------------------------
     */

    @GetMapping("/top-rated")
    public ResponseEntity<List<ProductResponseDto>> getTopRatedProducts() {
        return ResponseEntity.ok(productService.getTopRatedProducts());
    }

    /*
     -------------------------------------------------------
                    SEARCH PRODUCTS
     -------------------------------------------------------
     */

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDto>> searchProducts(
            @RequestParam String keyword) {

        return ResponseEntity.ok(productService.searchProducts(keyword));
    }

    /*
     -------------------------------------------------------
                    CATEGORY COUNT
     -------------------------------------------------------
     */

    @GetMapping("/count-by-category")
    public ResponseEntity<List<CategoryCountDto>> countProductsByCategory() {
        return ResponseEntity.ok(productService.countProductsByCategory());
    }

    /*
     -------------------------------------------------------
                    COMPANY COUNT
     -------------------------------------------------------
     */

    @GetMapping("/count-by-company")
    public ResponseEntity<List<CompanyCountDto>> countProductsByCompany() {
        return ResponseEntity.ok(productService.countProductsByCompany());
    }

    /*
     -------------------------------------------------------
                    STATUS COUNT
     -------------------------------------------------------
     */

    @GetMapping("/count-by-status")
    public ResponseEntity<List<StatusCountDto>> countProductsByStatus() {

        return ResponseEntity.ok(productService.countProductsByStatus());
    }
}
