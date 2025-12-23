package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.*;
@Entity
@Table(name = "diversity_classifications")
public class DiversityClassification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String description;
    private Boolean active;

    @ManyToMany(mappedBy = "diversityClassifications")
    private Set<Supplier> suppliers = new HashSet<>();

    @OneToMany(mappedBy = "classification")
    private Set<DiversityTarget> diversityTargets = new HashSet<>();

    public DiversityClassification() {}
    
    public DiversityClassification(Long id, String code, String description, Boolean active, Set<Supplier> suppliers, Set<DiversityTarget> diversityTargets) {

        this.code = code;
        this.description = description;
        this.active = active;
        this.suppliers = suppliers;
        this.diversityTargets = diversityTargets;
    }

    @PrePersist
    @PreUpdate
    protected void preSave() {
        if (this.active == null) this.active = true;
        if (this.code != null) this.code = this.code.toUpperCase();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    
    public Set<Supplier> getSuppliers() { return suppliers; }
    public void setSuppliers(Set<Supplier> suppliers) { this.suppliers = suppliers; }
    
    public Set<DiversityTarget> getDiversityTargets() { return diversityTargets; }
    public void setDiversityTargets(Set<DiversityTarget> diversityTargets) { this.diversityTargets = diversityTargets; }
}