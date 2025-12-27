package com.example.demo.service.impl;

import com.example.demo.entity.SpendCategory;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SpendCategoryRepository;
import com.example.demo.service.SpendCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class SpendCategoryServiceImpl implements SpendCategoryService {
    private final SpendCategoryRepository repository;
    
    public SpendCategoryServiceImpl(SpendCategoryRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public SpendCategory createCategory(SpendCategory category) {
        return repository.save(category);
    }
    
    @Override
    public SpendCategory updateCategory(Long id, SpendCategory category) {
        SpendCategory existing = getCategoryById(id);
        existing.setName(category.getName());
        existing.setDescription(category.getDescription());
        return repository.save(existing);
    }
    
    @Override
    public SpendCategory getCategoryById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }
    
    @Override
    public List<SpendCategory> getAllCategories() {
        return repository.findAll();
    }
    
    @Override
    public SpendCategory deactivateCategory(Long id) {
        SpendCategory category = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        category.setActive(false);
        return repository.save(category);
    }
}