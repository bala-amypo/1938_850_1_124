package com.example.demo.service;

import com.example.demo.entity.Supplier;
import java.util.List;

public interface SupplierService {
    Supplier createSupplier(Supplier supplier);
    
    // Changed from getSupplier to getSupplierById to match Controller
    Supplier getSupplierById(Long id); 
    
    List<Supplier> getAllSuppliers();
    
    Supplier deactivateSupplier(Long id);
}