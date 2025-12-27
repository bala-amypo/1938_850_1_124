package com.example.demo.service;

import com.example.demo.entity.Supplier;
import java.util.List;

public interface SupplierService {
    Supplier createSupplier(Supplier supplier);
    Supplier updateSupplier(Long id, Supplier supplier);
    Supplier getSupplierById(Long id);
    List<Supplier> getAllSuppliers();
    Supplier deactivateSupplier(Long id);
}