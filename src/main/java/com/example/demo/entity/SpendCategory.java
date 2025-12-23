package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.Set;
import lombok.*;
@Entity
@Table(name = "spend_categories")
public class SpendCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean active;

    @OneToMany(mappedBy = "category")
    private Set<PurchaseOrder> purchaseOrders;
    
    public SpendCategory() {}
    
    public SpendCategory(Long id, String name, Boolean active, Set<PurchaseOrder> purchaseOrders) {
        
        this.name = name;
        this.active = active;
        this.purchaseOrders = purchaseOrders;
    }

    @PrePersist
    @PreUpdate
    protected void preSave() {
        if (this.active == null) this.active = true;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public Set<PurchaseOrder> getPurchaseOrders() { return purchaseOrders; }
    public void setPurchaseOrders(Set<PurchaseOrder> purchaseOrders) { this.purchaseOrders = purchaseOrders; }
}