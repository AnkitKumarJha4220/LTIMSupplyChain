package com.wecp.progressive.dao;

import com.wecp.progressive.entity.Warehouse;

import java.sql.SQLException;
import java.util.List;

public interface WarehouseDAO {
    int addWarehouse(Warehouse warehouse) throws SQLException;
    Warehouse getWarehouseById(int warehouseId) throws SQLException;
    void updateWarehouse (Warehouse warehouse) throws SQLException;
    void deleteWarehouse (int warehouseId) throws SQLException;
    List<Warehouse> getAllWarehouse() throws SQLException;
}