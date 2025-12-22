package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "diversity_classifications")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @PrePersist
    @PreUpdate
    protected void preSave() {
        if (this.active == null) this.active = true;
        if (this.code != null) this.code = this.code.toUpperCase();
    }
}