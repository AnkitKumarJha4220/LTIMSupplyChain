package com.wecp.progressive.service;

import com.wecp.progressive.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(int productId);

    int addProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(int productId);

    //Do not implement these methods in ProductServiceImplJdbc.java class
    default List<Product> getAllProductByWarehouse(int warehouseId) {
        return null;
    }
}
