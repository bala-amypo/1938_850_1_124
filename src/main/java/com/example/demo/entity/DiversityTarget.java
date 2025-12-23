package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "diversity_targets")
public class DiversityTarget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int targetYear;
    private Double targetPercentage;
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "classification_id")
    private DiversityClassification classification;

    public DiversityTarget() {}
    
    public DiversityTarget(Long id, int targetYear, Double targetPercentage, Boolean active, DiversityClassification classification) {
    
        this.targetYear = targetYear;
        this.targetPercentage = targetPercentage;
        this.active = active;
        this.classification = classification;
    }

    @PrePersist
    @PreUpdate
    protected void preSave() {
        if (this.active == null) this.active = true;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getTargetYear() { return targetYear; }
    public void setTargetYear(int targetYear) { this.targetYear = targetYear; }
    public Double getTargetPercentage() { return targetPercentage; }
    public void setTargetPercentage(Double targetPercentage) { this.targetPercentage = targetPercentage; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public DiversityClassification getClassification() { return classification; }
    public void setClassification(DiversityClassification classification) { this.classification = classification; }
}