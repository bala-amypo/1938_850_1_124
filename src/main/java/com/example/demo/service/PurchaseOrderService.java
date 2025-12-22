package com.example.demo.service;

import com.example.demo.entity.PurchaseOrder;
import java.util.List;

public interface PurchaseOrderService {
    PurchaseOrder createPurchaseOrder(PurchaseOrder order);
    List<PurchaseOrder> getOrdersBySupplier(Long supplierId);
    List<PurchaseOrder> getOrdersByCategory(Long categoryId);
}