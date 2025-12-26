package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data; // Ensure Lombok is working to provide getPhone() and getAddress()
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Supplier {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String registrationNumber;
    private String email;
    private String phone; // Required for getPhone()
    private String address; // Required for getAddress()
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToMany
    private List<DiversityClassification> diversityClassifications;

    @PrePersist
    public void prePersist() {
        this.isActive = true; // Rule: isActive defaults to true
        this.createdAt = LocalDateTime.now(); // Rule: createdAt auto-generated
    }
}