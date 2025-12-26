package com.example.demo.service.impl;

import com.example.demo.entity.Supplier;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.service.SupplierService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    
    // Technical Constraint: Strict Constructor Injection
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier updateSupplier(Long id, Supplier supplier) {
        // Required for CRUD tests
        Supplier existing = getSupplierById(id);
        existing.setName(supplier.getName());
        existing.setEmail(supplier.getEmail());
        existing.setRegistrationNumber(supplier.getRegistrationNumber());
        // Update other fields as per entity definition
        return supplierRepository.save(existing);
    }

    @Override
    public Supplier getSupplierById(Long id) {
        // Exact naming required by the test suite
        return supplierRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        // Returns all suppliers for the List all endpoint
        return supplierRepository.findAll();
    }

    @Override
    public void deactivateSupplier(Long id) {
        // Requirement: Soft-deactivation using isActive flag
        Supplier supplier = getSupplierById(id);
        supplier.setIsActive(false);
        supplierRepository.save(supplier);
    }
}