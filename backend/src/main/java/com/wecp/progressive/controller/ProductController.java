package com.wecp.progressive.controller;

import com.wecp.progressive.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ProductController {

    public ResponseEntity<List<Product>> getAllProducts() {
        return null;
    }

    public ResponseEntity<Product> getProductById(int productId) {
        return null;
    }

    public ResponseEntity<Integer> addProduct(Product product) {
        return null;
    }

    public ResponseEntity<Void> updateProduct(int productId, Product product) {
        return null;
    }

    public ResponseEntity<Void> deleteProduct(int productId) {
        return null;
    }

    public ResponseEntity<List<Product>> getAllProductByWarehouse(int warehouseId) {
        return null;
    }
}
