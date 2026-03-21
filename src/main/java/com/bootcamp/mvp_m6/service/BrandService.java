package com.bootcamp.mvp_m6.service;

import com.bootcamp.mvp_m6.exceptions.InvalidOperationException;
import com.bootcamp.mvp_m6.model.Brand;
import com.bootcamp.mvp_m6.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;


    public Brand create(Brand brand) {
        if (brandRepository.existsByName(brand.getName())) {
            throw new InvalidOperationException("Ya existe un producto con el nombre: " + brand.getName());
        }

        return brandRepository.save(brand);
    }
}
