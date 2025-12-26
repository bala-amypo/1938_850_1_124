package com.example.demo.service.impl;

import com.example.demo.entity.SpendCategory;
import java.util.List;

public interface SpendCategoryService {
    SpendCategory createCategory(SpendCategory category);
    List<SpendCategory> getActiveCategories();
    SpendCategory getCategoryById(Long id);
}