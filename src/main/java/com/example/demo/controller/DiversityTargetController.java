package com.example.demo.controller;

import com.example.demo.entity.DiversityTarget;
import com.example.demo.service.DiversityTargetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/targets")
public class DiversityTargetController {

    private final DiversityTargetService targetService;

    // ADD THIS CONSTRUCTOR: This fixes the compilation error at line 105 of your test
    public DiversityTargetController(DiversityTargetService targetService) {
        this.targetService = targetService;
    }

    @PostMapping
    public ResponseEntity<DiversityTarget> create(@RequestBody DiversityTarget target) {
        return ResponseEntity.ok(targetService.createTarget(target));
    }

    @GetMapping("/active")
    public ResponseEntity<List<DiversityTarget>> getActive() {
        return ResponseEntity.ok(targetService.getActiveTargets());
    }
}