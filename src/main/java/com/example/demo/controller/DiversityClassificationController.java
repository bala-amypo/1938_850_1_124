package com.example.demo.controller;

import com.example.demo.entity.DiversityClassification;
import com.example.demo.service.DiversityClassificationService;
import org.springframework.beans.factory.annotation.Autowired; // Added import
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/classifications")
public class DiversityClassificationController {

    @Autowired // Field injection
    private DiversityClassificationService classificationService;

    @GetMapping
    public ResponseEntity<List<DiversityClassification>> getAll() {
        return ResponseEntity.ok(classificationService.getActiveClassifications());
    }

    @PostMapping
    public ResponseEntity<DiversityClassification> create(@RequestBody DiversityClassification classification) {
        return ResponseEntity.ok(classificationService.createClassification(classification));
    }
}