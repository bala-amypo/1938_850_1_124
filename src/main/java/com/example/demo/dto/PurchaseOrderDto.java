package com.example.demo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.math.BigDecimal;
import java.util.Date;

public class PurchaseOrderDto {

    @NotNull(message = "Supplier ID is required")
    private Long supplierId;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotNull(message = "Date issued is required")
    @PastOrPresent(message = "Date issued cannot be in the future")
    private Date dateIssued;
    
    public PurchaseOrderDto() {}
    
    public PurchaseOrderDto(Long supplierId, Long categoryId, BigDecimal amount, Date dateIssued) {
        this.supplierId = supplierId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.dateIssued = dateIssued;
    }
    
    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }
    
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    
    public Date getDateIssued() { return dateIssued; }
    public void setDateIssued(Date dateIssued) { this.dateIssued = dateIssued; }
}