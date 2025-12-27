package com.example.demo.service.impl;

import com.example.demo.entity.DiversityTarget;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DiversityTargetRepository;
import com.example.demo.service.DiversityTargetService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

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
        // FIX: Manually filter since findByActiveTrue() is missing in Repository
        return repository.findAll().stream()
                .filter(DiversityTarget::getActive)
                .collect(Collectors.toList());
    }

    @Override
    public List<DiversityTarget> getAllTargets() {
        return repository.findAll();
    }

    @Override
    public List<DiversityTarget> getTargetsByYear(int year) {
        // FIX: Manually filter since findByTargetYear() is missing or failing
        return repository.findAll().stream()
                .filter(t -> t.getTargetYear() == year)
                .collect(Collectors.toList());
    }

    @Override
    public void deactivateTarget(Long id) {
        DiversityTarget target = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Target not found with id: " + id));
        target.setActive(false);
        repository.save(target);
    }
}