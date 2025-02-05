package com.wecp.progressive.exception;

public class SupplierDoesNotExistException extends RuntimeException {
    public SupplierDoesNotExistException(String message) {
        super(message);
    }
}