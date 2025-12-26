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

    // TECHNICAL CONSTRAINT: Must use Constructor Injection
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    // FIX: Ensure this signature matches SupplierService interface exactly
    @Override
    public Supplier updateSupplier(Long id, Supplier supplier) {
        Supplier existing = getSupplier(id);
        existing.setName(supplier.getName());
        existing.setEmail(supplier.getEmail());
        existing.setRegistrationNumber(supplier.getRegistrationNumber());
        // Ensure fields like phone/address are updated if they exist in your entity
        return supplierRepository.save(existing);
    }

    @Override
    public Supplier getSupplier(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier deactivateSupplier(Long id) {
        // Requirement: Soft-deactivation using isActive flag
        Supplier supplier = getSupplier(id);
        supplier.setIsActive(false);
        return supplierRepository.save(supplier);
    }
}