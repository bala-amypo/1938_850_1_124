package com.example.demo.service;

import com.example.demo.entity.SpendCategory;
import java.util.List;

public interface SpendCategoryService {
    SpendCategory createCategory(SpendCategory category);
    // Add these two:
    List<SpendCategory> getAllCategories(); 
    void deactivateCategory(Long id);
}


package com.example.demo.service;

import com.example.demo.entity.SpendCategory;
import java.util.List;

public interface SpendCategoryService {
    SpendCategory createCategory(SpendCategory category);
    List<SpendCategory> getAllCategories();
    void deactivateCategory(Long id);
    
    // ADD THIS: Resolves errors in Controller and PurchaseOrderServiceImpl
    SpendCategory getCategoryById(Long id);
    
    // Ensure this is here if used in Impl
    List<SpendCategory> getActiveCategories(); 
}