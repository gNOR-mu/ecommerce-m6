package com.bootcamp.mvp_m6.repository;

import com.bootcamp.mvp_m6.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    boolean existsByName(String name);

    Optional<Brand> findByName(String name);
}
