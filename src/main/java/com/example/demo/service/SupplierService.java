package com.example.demo.service.impl;

import com.example.demo.entity.Supplier;
import java.util.List;

public interface SupplierService {
    Supplier createSupplier(Supplier supplier);
    Supplier getSupplier(Long id);
    List<Supplier> getAllSuppliers();
    Supplier deactivateSupplier(Long id);
}