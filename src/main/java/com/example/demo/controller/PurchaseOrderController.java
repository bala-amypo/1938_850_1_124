package com.example.demo.controller;

import com.example.demo.entity.PurchaseOrder;
import com.example.demo.service.PurchaseOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {

    private final PurchaseOrderService poService;

    public PurchaseOrderController(PurchaseOrderService poService) {
        this.poService = poService;
    }

    @PostMapping
    public ResponseEntity<PurchaseOrder> create(@RequestBody PurchaseOrder order) {
        return ResponseEntity.ok(poService.createPurchaseOrder(order));
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<PurchaseOrder>> getBySupplier(@PathVariable Long supplierId) {
        return ResponseEntity.ok(poService.getOrdersBySupplier(supplierId));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PurchaseOrder>> getByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(poService.getOrdersByCategory(categoryId));
    }
}