package com.bootcamp.mvp_m6.service;

import com.bootcamp.mvp_m6.exceptions.InvalidOperationException;
import com.bootcamp.mvp_m6.model.Brand;
import com.bootcamp.mvp_m6.repository.BrandRepository;
import jakarta.validation.constraints.NotNull;
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
            throw new InvalidOperationException("Ya existe un producto con el nombre: " + brand.getName());
        }

        return brandRepository.save(brand);
    }

    @Transactional(readOnly = true)
    public List<Brand> findAll(){
        return brandRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Brand getReferenceById(Long id){
        return brandRepository.getReferenceById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsById( Long brandId) {
        return brandRepository.existsById(brandId);
    }
}
