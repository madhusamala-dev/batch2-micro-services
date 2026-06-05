package java.com.trainingmug.ecommerce.productservice.controller;

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

import java.com.trainingmug.ecommerce.productservice.dto.response.ApiResponseDto;
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
    public ResponseEntity<ApiResponseDto<ProductResponseDto>> save(
            @RequestBody ProductRequestDto requestDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponseDto.<ProductResponseDto>builder().
                        success(true).
                        status(HttpStatus.CREATED.value()).
                        message("Product Created Successfully").
                        data(productService.save(requestDto)).
                        build());

    }

    /*
     -------------------------------------------------------
                    GET PRODUCT BY ID
     -------------------------------------------------------
     */

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponseDto<ProductResponseDto>> getProductById(
            @PathVariable String productId) {
        return ResponseEntity.ok(
                ApiResponseDto.<ProductResponseDto>builder().
                        success(true).
                        status(HttpStatus.OK.value()).
                        message("Product Retrieved Successfully").
                        data(productService.getById(productId)).
                        build());

    }

    /*
     -------------------------------------------------------
                    GET ALL PRODUCTS
     -------------------------------------------------------
     */

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<ProductResponseDto>>> getAll() {
        return ResponseEntity.ok(
                ApiResponseDto.<List<ProductResponseDto>>builder().
                        success(true).
                        status(HttpStatus.OK.value()).
                        message("Products Retrieved Successfully").
                        data(productService.getAll()).
                        build());

    }

    /*
     -------------------------------------------------------
                    UPDATE PRODUCT
     -------------------------------------------------------
     */

    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponseDto<ProductResponseDto>> update(
            @PathVariable String productId,
            @RequestBody ProductUpdateRequestDto requestDto) {

        return ResponseEntity.ok(
                ApiResponseDto.<ProductResponseDto>builder().
                        success(true).
                        status(HttpStatus.OK.value()).
                        message("Product Updated Successfully").
                        data(productService.update(productId, requestDto)).
                        build());


    }

    /*
     -------------------------------------------------------
                    DELETE PRODUCT
     -------------------------------------------------------
     */

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponseDto<Void>> delete(@PathVariable String productId) {
        productService.delete(productId);
        return ResponseEntity.ok(
                 ApiResponseDto.<Void>builder()
                         .success(true)
                         .status(HttpStatus.OK.value())
                         .message("Product Deleted Successfully")
                         .build()
                );
    }

    /*
     -------------------------------------------------------
                    PAGINATION & SORTING
     -------------------------------------------------------
     */

    @GetMapping("/pagination")
    public ResponseEntity<ApiResponseDto<Page<ProductResponseDto>>> getAllByPage(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy) {

        return ResponseEntity.ok(
                ApiResponseDto.<Page<ProductResponseDto>>builder().
                        success(true).
                        status(HttpStatus.OK.value()).
                        message("Products Retrieved Successfully").
                        data(productService.getAllByPage(page, size, sortBy)).
                        build());


    }

    /*
     -------------------------------------------------------
                    FILTER BY CATEGORY
     -------------------------------------------------------
     */

    @GetMapping("/category/{category}")
    public ResponseEntity<ApiResponseDto<List<ProductResponseDto>>> getProductsByCategory(
            @PathVariable Category category) {
        return ResponseEntity.ok(
                ApiResponseDto.<List<ProductResponseDto>>builder().
                        success(true).
                        status(HttpStatus.OK.value()).
                        message("Products Retrieved Successfully").
                        data(productService.getByCategory(category)).
                        build());

    }

    /*
     -------------------------------------------------------
                    FILTER BY STATUS
     -------------------------------------------------------
     */

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponseDto<List<ProductResponseDto>>> getProductsByStatus(
            @PathVariable Status status) {

        return ResponseEntity.ok(
                ApiResponseDto.<List<ProductResponseDto>>builder().
                        success(true).
                        status(HttpStatus.OK.value()).
                        message("Products Retrieved Successfully").
                        data(productService.getByStatus(status)).
                        build());

    }

    /*
     -------------------------------------------------------
                    TOP RATED PRODUCTS
     -------------------------------------------------------
     */

    @GetMapping("/top-rated")
    public ResponseEntity<ApiResponseDto<List<ProductResponseDto>>> getTopRatedProducts() {
        return ResponseEntity.ok(
                ApiResponseDto.<List<ProductResponseDto>>builder().
                        success(true).
                        status(HttpStatus.OK.value()).
                        message("Top Rated Products Retrieved Successfully").
                        data(productService.getTopRatedProducts()).
                        build());

    }

    /*
     -------------------------------------------------------
                    SEARCH PRODUCTS
     -------------------------------------------------------
     */

    @GetMapping("/search")
    public ResponseEntity<ApiResponseDto<List<ProductResponseDto>>> searchProducts(
            @RequestParam String keyword) {

        return ResponseEntity.ok(
                ApiResponseDto.<List<ProductResponseDto>>builder().
                        success(true).
                        status(HttpStatus.OK.value()).
                        message("Products Retrieved Successfully").
                        data(productService.searchProducts(keyword)).
                        build());

    }

    /*
     -------------------------------------------------------
                    CATEGORY COUNT
     -------------------------------------------------------
     */

    @GetMapping("/count-by-category")
    public ResponseEntity<ApiResponseDto<List<CategoryCountDto>>> countProductsByCategory() {
        return ResponseEntity.ok(
                ApiResponseDto.<List<CategoryCountDto>>builder().
                        success(true).
                        status(HttpStatus.OK.value()).
                        message("Category Count Retrieved Successfully").
                        data(productService.countProductsByCategory()).
                        build());

    }

    /*
     -------------------------------------------------------
                    COMPANY COUNT
     -------------------------------------------------------
     */

    @GetMapping("/count-by-company")
    public ResponseEntity<ApiResponseDto<List<CompanyCountDto>>> countProductsByCompany() {
        return ResponseEntity.ok(
                ApiResponseDto.<List<CompanyCountDto>>builder().
                        success(true).
                        status(HttpStatus.OK.value()).
                        message("Company Count Retrieved Successfully").
                        data(productService.countProductsByCompany()).
                        build());

    }

    /*
     -------------------------------------------------------
                    STATUS COUNT
     -------------------------------------------------------
     */

    @GetMapping("/count-by-status")
    public ResponseEntity<ApiResponseDto<List<StatusCountDto>>> countProductsByStatus() {

        return ResponseEntity.ok(
                ApiResponseDto.<List<StatusCountDto>>builder().
                        success(true).
                        status(HttpStatus.OK.value()).
                        message("Status Count Retrieved Successfully").
                        data(productService.countProductsByStatus()).
                        build());

    }
}
