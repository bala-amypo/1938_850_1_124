package com.example.demo.controller;

import com.example.demo.entity.DiversityTarget;
import com.example.demo.service.DiversityTargetService;
import org.springframework.beans.factory.annotation.Autowired; // Added import
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/targets")
public class DiversityTargetController {

    @Autowired // Field injection replaces the constructor
    private DiversityTargetService targetService;

    @PostMapping
    public ResponseEntity<DiversityTarget> create(@RequestBody DiversityTarget target) {
        return ResponseEntity.ok(targetService.createTarget(target));
    }

    @GetMapping("/active")
    public ResponseEntity<List<DiversityTarget>> getActive() {
        return ResponseEntity.ok(targetService.getActiveTargets());
    }
}