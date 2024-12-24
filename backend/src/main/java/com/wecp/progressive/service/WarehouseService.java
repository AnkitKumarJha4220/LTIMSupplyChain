package com.wecp.progressive.service;

import com.wecp.progressive.entity.Warehouse;

import java.util.List;

public interface WarehouseService {
    List<Warehouse> getAllWarehouses();

    int addWarehouse(Warehouse warehouse);

    List<Warehouse> getWarehousesSortedByCapacity();

    default public void emptyArrayList() {
    }

    //Do not implement these methods in WarehouseServiceImplArraylist.java class
    default void updateWarehouse(Warehouse warehouse) {
    }

    default void deleteWarehouse(int warehouseId) {
    }

    default Warehouse getWarehouseById(int warehouseId) {
        return null;
    }

    //Do not implement these methods in WarehouseServiceImplArraylist.java and WarehouseServiceImplJdbc.java class
    default List<Warehouse> getWarehouseBySupplier(int supplierId) {
        return null;
    }
}
