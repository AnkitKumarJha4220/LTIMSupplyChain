package com.wecp.progressive.exception;

public class SupplierAlreadyExistsException extends RuntimeException {
    public SupplierAlreadyExistsException(String message) {
        super(message);
    }
}