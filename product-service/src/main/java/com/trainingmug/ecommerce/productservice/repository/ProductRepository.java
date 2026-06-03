package com.trainingmug.ecommerce.productservice.repository;

import com.trainingmug.ecommerce.productservice.entity.Product;
import com.trainingmug.ecommerce.productservice.enums.Category;
import com.trainingmug.ecommerce.productservice.enums.Status;
import com.trainingmug.ecommerce.productservice.projection.CategoryCountResponse;
import com.trainingmug.ecommerce.productservice.projection.CompanyCountResponse;
import com.trainingmug.ecommerce.productservice.projection.StatusCountResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
 /*
     -------------------------------------------------------
                    BASIC FINDER METHODS
     -------------------------------------------------------
     */

    Optional<Product> findByName(String name);

    List<Product> findByCompany(String company);

    List<Product> findByCategory(Category category);

    List<Product> findByStatus(Status status);

    /*
     -------------------------------------------------------
                    PRICE FILTERING
     -------------------------------------------------------
     */

    List<Product> findByMaxRetailPriceBetween(
            int minPrice,
            int maxPrice
    );

    List<Product> findByMaxRetailPriceLessThan(int price);

    List<Product> findByMaxRetailPriceGreaterThan(int price);

    List<Product> findByDiscountPercentageGreaterThan(byte discountPercentage);

    /*
     -------------------------------------------------------
                    TOP RATED PRODUCTS
     -------------------------------------------------------
     */

    List<Product> findTop10ByOrderByRatingDesc();

    List<Product> findTop5ByCategoryOrderByRatingDesc(
            Category category
    );

    List<Product> findTop10ByStatusOrderByRatingDesc(
            Status status
    );

    /*
     -------------------------------------------------------
                    SEARCHING
     -------------------------------------------------------
     */

    List<Product> findByNameContainingIgnoreCase(String keyword);

    List<Product> findByCompanyContainingIgnoreCase(
            String keyword
    );

    /*
     -------------------------------------------------------
                    PAGINATION
     -------------------------------------------------------
     */

    Page<Product> findAll(Pageable pageable);

    Page<Product> findByCategory(
            Status category,
            Pageable pageable
    );

    Page<Product> findByStatus(
            Status status,
            Pageable pageable
    );

    Page<Product> findByCompany(
            String company,
            Pageable pageable
    );



    /*
     -------------------------------------------------------
                    SORTING
     -------------------------------------------------------
     */

    List<Product> findAllByOrderByMaxRetailPriceAsc();

    List<Product> findAllByOrderByMaxRetailPriceDesc();

    List<Product> findAllByOrderByRatingDesc();

    List<Product> findAllByOrderByCreatedDateDesc();

    /*
     -------------------------------------------------------
                    COUNT QUERIES
     -------------------------------------------------------
     */

    long countByCategory(Category category);

    long countByStatus(Status status);

    long countByCompany(String company);


    /*
     -------------------------------------------------------
                    AGGREGATION QUERIES
     -------------------------------------------------------
     */

    @Aggregation(pipeline = {
            """
            {
                $group: {
                    _id: "$category",
                    totalProducts: { $sum: 1 }
                }
            }
            """,
            """
            {
                $project: {
                    category: "$_id",
                    totalProducts: 1,
                    _id: 0
                }
            }
            """
    })
    List<CategoryCountResponse> countProductsByCategory();

    @Aggregation(pipeline = {
            """
            {
                $group: {
                    _id: "$company",
                    totalProducts: { $sum: 1 }
                }
            }
            """,
            """
            {
                $project: {
                    company: "$_id",
                    totalProducts: 1,
                    _id: 0
                }
            }
            """
    })
    List<CompanyCountResponse> countProductsByCompany();

    @Aggregation(pipeline = {
            """
            {
                $group: {
                    _id: "$status",
                    totalProducts: { $sum: 1 }
                }
            }
            """,
            """
            {
                $project: {
                    status: "$_id",
                    totalProducts: 1,
                    _id: 0
                }
            }
            """
    })
    List<StatusCountResponse> countProductsByStatus();

    /*
     -------------------------------------------------------
                    REVIEW / RATING QUERIES
     -------------------------------------------------------
     */

    List<Product> findByRatingGreaterThanEqual(Float rating);

    List<Product> findByReviewsCountGreaterThan(Integer count);

    /*
     -------------------------------------------------------
                    RECENT PRODUCTS
     -------------------------------------------------------
     */

    List<Product> findTop10ByOrderByCreatedDateDesc();

    /*
     -------------------------------------------------------
                    COMBINED QUERIES
     -------------------------------------------------------
     */

    List<Product> findByCategoryAndStatus(
            Category category,
            Status status
    );

    List<Product> findByCategoryAndMaxRetailPriceBetween(
            Category category,
            int minPrice,
            int maxPrice
    );

    List<Product> findByCategoryAndRatingGreaterThanEqual(
            Category category,
            float rating
    );
}



