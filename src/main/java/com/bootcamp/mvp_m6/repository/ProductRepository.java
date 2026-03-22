package com.bootcamp.mvp_m6.repository;

import com.bootcamp.mvp_m6.dto.product.ProductResumeDTO;
import com.bootcamp.mvp_m6.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
            SELECT new com.bootcamp.mvp_m6.dto.product.ProductResumeDTO(id, name, shortDescription, urlImage, price)
            FROM Product p
            """)
    List<ProductResumeDTO> findAllResume();

}
