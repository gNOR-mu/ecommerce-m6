package com.bootcamp.mvp_m6.service;

import com.bootcamp.mvp_m6.exceptions.InvalidOperationException;
import com.bootcamp.mvp_m6.model.Category;
import com.bootcamp.mvp_m6.repository.CategoryRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;


    @Transactional
    public Category create(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new InvalidOperationException("Ya existe una categoría con el nombre: " + category.getName());
        }
        return categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Category getReferenceById(Long id){
        return categoryRepository.getReferenceById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsById(Long categoryId) {
        return categoryRepository.existsById(categoryId);
    }
}
