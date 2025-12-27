package com.example.demo.service.impl;

import com.example.demo.entity.PurchaseOrder;
import com.example.demo.entity.Supplier;
import com.example.demo.entity.SpendCategory;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.PurchaseOrderRepository;
import com.example.demo.service.PurchaseOrderService;
import com.example.demo.service.SupplierService;
import com.example.demo.service.SpendCategoryService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final SupplierService supplierService;
    private final SpendCategoryService spendCategoryService;
    
    public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository, SupplierService supplierService, SpendCategoryService spendCategoryService) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.supplierService = supplierService;
        this.spendCategoryService = spendCategoryService;
    }

    @Override
    public PurchaseOrder createPurchaseOrder(PurchaseOrder order) {
        Supplier supplier = supplierService.getSupplierById(order.getSupplier().getId());
        if (!supplier.getIsActive()) throw new BadRequestException("Supplier is inactive");

        SpendCategory category = spendCategoryService.getCategoryById(order.getCategory().getId());
        if (!category.getActive()) throw new BadRequestException("Category is inactive");

        if (order.getAmount() == null || order.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Amount must be positive");
        }
        return purchaseOrderRepository.save(order);
    }

    @Override
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    @Override
    public List<PurchaseOrder> getPurchaseOrdersBySupplier(Long supplierId) {
        // FIX: Manually filter since findBySupplier_Id() is missing in Repository
        return purchaseOrderRepository.findAll().stream()
                .filter(po -> po.getSupplier() != null && po.getSupplier().getId().equals(supplierId))
                .collect(Collectors.toList());
    }

    @Override
    public List<PurchaseOrder> getOrdersByCategory(Long categoryId) {
        // FIX: Manually filter since findByCategory_Id() is missing in Repository
        return purchaseOrderRepository.findAll().stream()
                .filter(po -> po.getCategory() != null && po.getCategory().getId().equals(categoryId))
                .collect(Collectors.toList());
    }
}