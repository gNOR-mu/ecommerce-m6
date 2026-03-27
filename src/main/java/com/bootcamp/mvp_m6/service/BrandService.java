package com.bootcamp.mvp_m6.service;

import com.bootcamp.mvp_m6.exceptions.ResourceAlreadyExistsException;
import com.bootcamp.mvp_m6.model.Brand;
import com.bootcamp.mvp_m6.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;


    @Transactional
    public Brand create(Brand brand) {
        if (brandRepository.existsByName(brand.getName())) {
            throw new ResourceAlreadyExistsException("Brand", "name", brand.getName());
        }

        return brandRepository.save(brand);
    }

    @Transactional
    public Brand findOrCreate(String brandName) {
        return brandRepository.findByName(brandName).orElseGet(
                () -> brandRepository.save(Brand.builder().name(brandName).build())
        );
    }

    @Transactional(readOnly = true)
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Brand getReferenceById(Long id) {
        return brandRepository.getReferenceById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsById(Long brandId) {
        return brandRepository.existsById(brandId);
    }
}
