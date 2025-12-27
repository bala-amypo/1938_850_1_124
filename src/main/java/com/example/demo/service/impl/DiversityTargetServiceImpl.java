package com.example.demo.service.impl;

import com.example.demo.entity.DiversityTarget;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DiversityTargetRepository;
import com.example.demo.service.DiversityTargetService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DiversityTargetServiceImpl implements DiversityTargetService {
    private final DiversityTargetRepository repository;
    
    public DiversityTargetServiceImpl(DiversityTargetRepository repository) {
        this.repository = repository;
    }

    @Override
    public DiversityTarget createTarget(DiversityTarget target) {
        if (target.getTargetPercentage() < 0 || target.getTargetPercentage() > 100) {
            throw new BadRequestException("Percentage must be within [0, 100]");
        }
        return repository.save(target);
    }

    @Override
    public List<DiversityTarget> getActiveTargets() {
        return repository.findByActiveTrue();
    }

    // ADDED: Controller expects this method
    @Override
    public List<DiversityTarget> getAllTargets() {
        return repository.findAll();
    }

    @Override
    public List<DiversityTarget> getTargetsByYear(int year) {
        return repository.findByTargetYear(year);
    }

    @Override
    public void deactivateTarget(Long id) {
        if (id == null) {
            throw new ResourceNotFoundException("Target ID cannot be null");
        }
        DiversityTarget target = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Target not found with id: " + id));
        target.setActive(false);
        repository.save(target);
    }
}