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

    // TECHNICAL CONSTRAINT: Constructor Injection is mandatory
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier updateSupplier(Long id, Supplier supplier) {
        Supplier existing = getSupplier(id);
        existing.setName(supplier.getName());
        existing.setEmail(supplier.getEmail());
        existing.setRegistrationNumber(supplier.getRegistrationNumber());
        return supplierRepository.save(existing);
    }

    // FIX: Renamed from getSupplierById to getSupplier to match your interface
    @Override
    public Supplier getSupplier(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    // FIX: Renamed to match the exact method signature expected
    @Override
    public Supplier deactivateSupplier(Long id) {
        Supplier supplier = getSupplier(id);
        supplier.setIsActive(false); // Requirement: Soft-deactivation
        return supplierRepository.save(supplier);
    }

    // This method ensures the test suite call for 'getSupplierById' also works
    public Supplier getSupplierById(Long id) {
        return getSupplier(id);
    }
}