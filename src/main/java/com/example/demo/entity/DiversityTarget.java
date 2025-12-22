package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "diversity_targets")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @PrePersist
    @PreUpdate
    protected void preSave() {
        if (this.active == null) this.active = true;
    }
}