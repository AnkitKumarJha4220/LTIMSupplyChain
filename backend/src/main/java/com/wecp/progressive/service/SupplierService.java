package com.wecp.progressive.service;


import com.wecp.progressive.entity.Supplier;

import java.util.List;

public interface SupplierService {
    List<Supplier> getAllSuppliers();

    int addSupplier(Supplier supplier);

    List<Supplier> getAllSuppliersSortedByName();

    default public void emptyArrayList() {
    }

    //Do not implement these methods in SupplierServiceImplArraylist.java class
    default void updateSupplier(Supplier supplier) {
    }

    default void deleteSupplier(int supplierId) {
    }

    default Supplier getSupplierById(int supplierId) {
        return null;
    }

}
