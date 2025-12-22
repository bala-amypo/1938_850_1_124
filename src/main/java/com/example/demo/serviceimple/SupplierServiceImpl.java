package com.example.demo.serviceimple;

import com.example.demo.entity.Supplier;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.service.SupplierService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Supplier createSupplier(Supplier supplier) {
        if (supplier == null) {
            throw new IllegalArgumentException("Supplier cannot be null");
        }
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier getSupplier(Long id) {
        if (id == null) {
            throw new ResourceNotFoundException("Supplier ID cannot be null");
        }
        return supplierRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + id));
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier deactivateSupplier(Long id) {
        Supplier supplier = getSupplier(id);
        supplier.setIsActive(false);
        return supplierRepository.save(supplier);
    }

    public Supplier getSupplierById(Long id) {
        return getSupplier(id);
    }
}