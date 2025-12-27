package com.example.demo.service;

import com.example.demo.entity.DiversityClassification;
import java.util.List;

public interface DiversityClassificationService {
    DiversityClassification createClassification(DiversityClassification classification);
    
    // This was named getActiveClassifications(), 
    // but the Controller expects getAllClassifications()
    List<DiversityClassification> getAllClassifications();

    // Add these missing methods:
    DiversityClassification getClassificationById(Long id);
    
    void deactivateClassification(Long id);
}