package com.example.demo.service;

import com.example.demo.entity.Supplier;
import java.util.List;

public interface SupplierService {
    Supplier createSupplier(Supplier supplier);
    
    // RENAME THIS to match what the test expects
    Supplier getSupplierById(long id); 
    
    List<Supplier> getAllSuppliers();
    Supplier deactivateSupplier(Long id);
}