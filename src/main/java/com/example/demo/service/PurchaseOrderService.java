package com.example.demo.service;

import com.example.demo.entity.PurchaseOrder;
import java.util.List;

public interface PurchaseOrderService {
    PurchaseOrder createPurchaseOrder(PurchaseOrder order);

    // RENAMED: Match the Controller's call 'getPurchaseOrdersBySupplier'
    List<PurchaseOrder> getPurchaseOrdersBySupplier(Long supplierId);

    // ADDED: The Controller is looking for 'getAllPurchaseOrders'
    List<PurchaseOrder> getAllPurchaseOrders();

    // KEEP/UPDATE: Ensure this matches your implementation logic
    List<PurchaseOrder> getOrdersByCategory(Long categoryId);
}