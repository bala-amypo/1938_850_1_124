package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*; // Import Lombok annotations
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "diversity_classifications")
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@Builder // Useful for creating objects in your tests or services
public class DiversityClassification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String description;
    private Boolean active;

    @ManyToMany(mappedBy = "diversityClassifications")
    @Builder.Default // Ensures the set is initialized when using @Builder
    private Set<Supplier> suppliers = new HashSet<>();

    @OneToMany(mappedBy = "classification")
    @Builder.Default
    private Set<DiversityTarget> diversityTargets = new HashSet<>();

    @PrePersist
    @PreUpdate
    protected void preSave() {
        if (this.active == null) this.active = true;
        if (this.code != null) this.code = this.code.toUpperCase();
    }
}