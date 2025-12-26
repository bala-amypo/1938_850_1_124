package com.example.demo.service.impl.SpendCategoryServiceImpl;

import com.example.demo.entity.SpendCategory;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SpendCategoryRepository;
import com.example.demo.service.SpendCategoryService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SpendCategoryServiceImpl implements SpendCategoryService {
    private final SpendCategoryRepository repository;
    
    public SpendCategoryServiceImpl(SpendCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public SpendCategory createCategory(SpendCategory category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        return repository.save(category);
    }

    @Override
    public List<SpendCategory> getActiveCategories() {
        return repository.findByActiveTrue();
    }

    public List<SpendCategory> getAllCategories() {
        return repository.findAll();
    }

    @Override
    public SpendCategory getCategoryById(Long id) {
        if (id == null) {
            throw new ResourceNotFoundException("Category ID cannot be null");
        }
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    public void deactivateCategory(Long id) {
        SpendCategory category = getCategoryById(id);
        category.setActive(false);
        repository.save(category);
    }
}