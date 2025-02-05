package com.wecp.progressive.service.impl;


import com.wecp.progressive.entity.Supplier;
import com.wecp.progressive.exception.SupplierAlreadyExistsException;
import com.wecp.progressive.exception.SupplierDoesNotExistException;
import com.wecp.progressive.repository.ProductRepository;
import com.wecp.progressive.repository.ShipmentRepository;
import com.wecp.progressive.repository.SupplierRepository;
import com.wecp.progressive.repository.WarehouseRepository;
import com.wecp.progressive.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Service
public class SupplierServiceImplJpa implements SupplierService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ShipmentRepository shipmentRepository;

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImplJpa(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Supplier> getAllSuppliers() throws SQLException {
        return supplierRepository.findAll();
    }

    @Override
    public int addSupplier(Supplier supplier) throws SupplierAlreadyExistsException {
        Supplier oldUser = supplierRepository.findByUsername(supplier.getUsername());
        if (oldUser != null) {
            throw new SupplierAlreadyExistsException("User name Is Unavailable: " + supplier.getUsername());
        }
        Supplier existingEmail = supplierRepository.findByEmail(supplier.getEmail());
        if (existingEmail != null) {
            throw new SupplierAlreadyExistsException("User with the given email address already exists: " + supplier.getEmail());
        }
        supplier.setPassword(passwordEncoder.encode(supplier.getPassword()));
        return supplierRepository.save(supplier).getSupplierId();
    }

    @Override
    public List<Supplier> getAllSuppliersSortedByName() throws SQLException {
        List<Supplier> sortedSuppliers = supplierRepository.findAll();
        Collections.sort(sortedSuppliers);
        return sortedSuppliers;
    }

    @Override
    public void updateSupplier(Supplier supplier) throws SupplierAlreadyExistsException {
        if (!supplier.getRole().isBlank()) {
            Supplier oldUser = supplierRepository.findByUsername(supplier.getUsername());
           if (oldUser != null && oldUser.getSupplierId() != supplier.getSupplierId()) {
                throw new SupplierAlreadyExistsException("User name Is Unavailable: " + supplier.getUsername());
            }
            if (!oldUser.getPassword().equals(supplier.getPassword())) {
                supplier.setPassword(passwordEncoder.encode(supplier.getPassword()));
            }
            supplierRepository.save(supplier);
        }
    }

    @Override
    @Transactional
    public void deleteSupplier(int supplierId) throws SQLException {
        shipmentRepository.deleteBySupplierId(supplierId);
        productRepository.deleteBySupplierId(supplierId);
        warehouseRepository.deleteBySupplierId(supplierId);
        supplierRepository.deleteBySupplierId(supplierId);
    }

    @Override
    public Supplier getSupplierById(int supplierId) throws SupplierDoesNotExistException {
        Supplier supplier = supplierRepository.findBySupplierId(supplierId);
        if (supplier != null) {
            return supplierRepository.findBySupplierId(supplierId);
        }
        throw new SupplierDoesNotExistException("Supplier with the given supplierId does not exists");
    }
}