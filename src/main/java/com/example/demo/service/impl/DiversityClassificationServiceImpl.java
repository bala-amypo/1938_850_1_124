package com.example.demo.service.impl;

import com.example.demo.entity.DiversityClassification;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DiversityClassificationRepository;
import com.example.demo.service.DiversityClassificationService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DiversityClassificationServiceImpl implements DiversityClassificationService {
    private final DiversityClassificationRepository repository;
    
    public DiversityClassificationServiceImpl(DiversityClassificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public DiversityClassification createClassification(DiversityClassification classification) {
        return repository.save(classification);
    }

    @Override
    public List<DiversityClassification> getAllClassifications() {
        return repository.findAll();
    }

    @Override
    public DiversityClassification getClassificationById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Classification not found"));
    }

    @Override
    public void deactivateClassification(Long id) {
        DiversityClassification classification = getClassificationById(id);
        classification.setActive(false);
        repository.save(classification);
    }
}