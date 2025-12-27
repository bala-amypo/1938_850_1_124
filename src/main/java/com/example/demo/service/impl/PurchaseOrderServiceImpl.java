package com.example.demo.service.impl;

import com.example.demo.entity.PurchaseOrder;
import com.example.demo.entity.Supplier;
import com.example.demo.entity.SpendCategory;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PurchaseOrderRepository;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.repository.SpendCategoryRepository;
import com.example.demo.service.PurchaseOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final SupplierRepository supplierRepository;
    private final SpendCategoryRepository spendCategoryRepository;
    
    public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository,
                                   SupplierRepository supplierRepository,
                                   SpendCategoryRepository spendCategoryRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.supplierRepository = supplierRepository;
        this.spendCategoryRepository = spendCategoryRepository;
    }
    
    @Override
    public PurchaseOrder createPurchaseOrder(PurchaseOrder order) {
        // Validate amount
        if (order.getAmount() == null || order.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Amount must be positive");
        }
        
        // Validate date
        if (order.getDateIssued() != null && order.getDateIssued().isAfter(LocalDate.now())) {
            throw new BadRequestException("Date cannot be in future");
        }
        
        // Validate supplier
        if (order.getSupplier() != null && order.getSupplier().getId() != null) {
            Supplier supplier = supplierRepository.findById(order.getSupplier().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
            if (!supplier.getIsActive()) {
                throw new BadRequestException("Supplier is not active");
            }
        }
        
        // Validate category
        if (order.getCategory() != null && order.getCategory().getId() != null) {
            SpendCategory category = spendCategoryRepository.findById(order.getCategory().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            if (!category.getActive()) {
                throw new BadRequestException("Category is not active");
            }
        }
        
        return purchaseOrderRepository.save(order);
    }
    @Override
    public PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrder order) {
        PurchaseOrder existing = getPurchaseOrderById(id);
        existing.setPoNumber(order.getPoNumber());
        existing.setAmount(order.getAmount());
        existing.setDateIssued(order.getDateIssued());
        existing.setApprovedBy(order.getApprovedBy());
        existing.setNotes(order.getNotes());
        return purchaseOrderRepository.save(existing);
    }
    
    @Override
    public PurchaseOrder getPurchaseOrderById(Long id) {
        return purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase Order not found"));
    }
    
    @Override
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }
    
    @Override
    public List<PurchaseOrder> getPurchaseOrdersBySupplier(Long supplierId) {
        return purchaseOrderRepository.findBySupplier_Id(supplierId);
    }
}