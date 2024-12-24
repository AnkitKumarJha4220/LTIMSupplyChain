package com.wecp.progressive.entity;

public class Warehouse {
    int warehouseId;
    int supplierId;
    String warehouseNmae;
    String location;
    int capacity;

    public Warehouse() {
    }

    public Warehouse(int warehouseId, int supplierId, String warehouseNmae, String location, int capacity) {
        this.warehouseId = warehouseId;
        this.supplierId = supplierId;
        this.warehouseNmae = warehouseNmae;
        this.location = location;
        this.capacity = capacity;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getWarehouseNmae() {
        return warehouseNmae;
    }

    public void setWarehouseNmae(String warehouseNmae) {
        this.warehouseNmae = warehouseNmae;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    

}