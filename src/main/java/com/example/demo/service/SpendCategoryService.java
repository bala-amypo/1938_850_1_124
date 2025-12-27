package com.example.demo.service;

import com.example.demo.entity.SpendCategory;
import java.util.List;

public interface SpendCategoryService {
    SpendCategory createCategory(SpendCategory category);
    // Add these two:
    List<SpendCategory> getAllCategories(); 
    void deactivateCategory(Long id);
}