package com.example.demo.serviceimpl;

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
        if (classification == null) {
            throw new IllegalArgumentException("Classification cannot be null");
        }
        return repository.save(classification);
    }

    @Override
    public List<DiversityClassification> getActiveClassifications() {
        return repository.findByActiveTrue();
    }

    public List<DiversityClassification> getAllClassifications() {
        return repository.findAll();
    }

    public void deactivateClassification(Long id) {
        if (id == null) {
            throw new ResourceNotFoundException("Classification ID cannot be null");
        }
        DiversityClassification classification = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Classification not found"));
        classification.setActive(false);
        repository.save(classification);
    }
}