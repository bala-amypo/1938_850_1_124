package com.example.demo.service;

import com.example.demo.entity.PurchaseOrder;
import java.util.List;

public interface PurchaseOrderService {
    PurchaseOrder createPurchaseOrder(PurchaseOrder order);
    PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrder order);
    PurchaseOrder getPurchaseOrderById(Long id);
    List<PurchaseOrder> getAllPurchaseOrders();
    List<PurchaseOrder> getPurchaseOrdersBySupplier(Long supplierId);
}