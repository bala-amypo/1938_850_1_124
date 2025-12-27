package com.example.demo.service;

import com.example.demo.entity.DiversityTarget;
import java.util.List;

public interface DiversityTargetService {
    DiversityTarget createTarget(DiversityTarget target);
    
    List<DiversityTarget> getActiveTargets();

    // ADDED: To resolve "cannot find symbol: method getAllTargets()"
    List<DiversityTarget> getAllTargets();

    // ADDED: To resolve "cannot find symbol: method getTargetsByYear(int)"
    List<DiversityTarget> getTargetsByYear(int year);

    // ADDED: To resolve "cannot find symbol: method deactivateTarget(java.lang.Long)"
    void deactivateTarget(Long id);
}