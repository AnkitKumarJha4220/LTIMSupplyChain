package com.wecp.progressive.exception;

public class NoWarehouseFoundForSupplierException extends RuntimeException {
    public NoWarehouseFoundForSupplierException(String message) {
        super(message);
    }
}