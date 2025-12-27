package com.example.demo.service.impl;

import com.example.demo.entity.DiversityTarget;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DiversityTargetRepository;
import com.example.demo.service.DiversityTargetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class DiversityTargetServiceImpl implements DiversityTargetService {
    private final DiversityTargetRepository repository;
    
    public DiversityTargetServiceImpl(DiversityTargetRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public DiversityTarget createTarget(DiversityTarget target) {
        return repository.save(target);
    }
    
    @Override
    public DiversityTarget updateTarget(Long id, DiversityTarget target) {
        DiversityTarget existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Target not found"));
        existing.setTargetYear(target.getTargetYear());
        existing.setTargetPercentage(target.getTargetPercentage());
        existing.setClassification(target.getClassification());
        return repository.save(existing);
    }
    
    @Override
    public List<DiversityTarget> getAllTargets() {
        return repository.findAll();
    }
    
    @Override
    public List<DiversityTarget> getTargetsByYear(Integer year) {
        return repository.findByTargetYear(year);
    }
    
    @Override
    public DiversityTarget deactivateTarget(Long id) {
        DiversityTarget target = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Target not found"));
        target.setActive(false);
        return repository.save(target);
    }
}