package com.wecp.progressive.exception;

public class InsufficientCapacityException extends RuntimeException{
    public InsufficientCapacityException(String message) {
        super(message);
    }
}