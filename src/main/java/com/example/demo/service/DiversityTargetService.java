package com.example.demo.service;

import com.example.demo.entity.DiversityTarget;
import java.util.List;

public interface DiversityTargetService {
    DiversityTarget createTarget(DiversityTarget target);
    DiversityTarget updateTarget(Long id, DiversityTarget target);
    List<DiversityTarget> getAllTargets();
    List<DiversityTarget> getTargetsByYear(Integer year);
    DiversityTarget deactivateTarget(Long id);
}