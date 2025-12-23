package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String poNumber;
    private BigDecimal amount;
    private LocalDate dateIssued;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private SpendCategory category;
    
    public PurchaseOrder() {}
    
    public PurchaseOrder(Long id, String poNumber, BigDecimal amount, LocalDate dateIssued, String notes, Supplier supplier, SpendCategory category) {
        this.poNumber = poNumber;
        this.amount = amount;
        this.dateIssued = dateIssued;
        this.notes = notes;
        this.supplier = supplier;
        this.category = category;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPoNumber() { return poNumber; }
    public void setPoNumber(String poNumber) { this.poNumber = poNumber; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public LocalDate getDateIssued() { return dateIssued; }
    public void setDateIssued(LocalDate dateIssued) { this.dateIssued = dateIssued; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public Supplier getSupplier() { return supplier; }
    public void setSupplier(Supplier supplier) { this.supplier = supplier; }
    public SpendCategory getCategory() { return category; }
    public void setCategory(SpendCategory category) { this.category = category; }
}