package com.wecp.progressive.controller;

import com.wecp.progressive.entity.Warehouse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class WarehouseController {

    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        return null;
    }

    public ResponseEntity<Warehouse> getWarehouseById(int warehouseId) {
        return null;
    }

    public ResponseEntity<Integer> addWarehouse(Warehouse warehouse) {
        return null;
    }

    public ResponseEntity<Void> updateWarehouse(int warehouseId, Warehouse warehouse) {
        return null;
    }

    public ResponseEntity<Void> deleteWarehouse(int warehouseId) {
        return null;
    }


    public ResponseEntity<List<Warehouse>> getWarehousesBySupplier(int supplierId) {
        return null;
    }
}
