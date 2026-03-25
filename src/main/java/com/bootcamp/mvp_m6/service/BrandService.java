package com.bootcamp.mvp_m6.service;

import com.bootcamp.mvp_m6.exceptions.InvalidOperationException;
import com.bootcamp.mvp_m6.model.Brand;
import com.bootcamp.mvp_m6.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Brand> findAll(){
        return brandRepository.findAll();
    }

    public Brand getReferenceById(Long id){
        return brandRepository.getReferenceById(id);
    }
}
