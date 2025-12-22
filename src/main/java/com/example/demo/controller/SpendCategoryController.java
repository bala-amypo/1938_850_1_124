package com.example.demo.controller;

import com.example.demo.entity.SpendCategory;
import com.example.demo.service.SpendCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class SpendCategoryController {

    private final SpendCategoryService categoryService;

    public SpendCategoryController(SpendCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<SpendCategory> create(@RequestBody SpendCategory category) {
        return ResponseEntity.ok(categoryService.createCategory(category));
    }

    @GetMapping("/active")
    public ResponseEntity<List<SpendCategory>> getActive() {
        return ResponseEntity.ok(categoryService.getActiveCategories());
    }
}