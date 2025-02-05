package com.wecp.progressive.service;

import com.wecp.progressive.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {

    List<Product> getAllProducts() throws SQLException;

    Product getProductById(int productId) throws SQLException;

    int addProduct(Product Product) throws SQLException;

    void updateProduct(Product Product) throws SQLException;

    void deleteProduct(int productId) throws SQLException;

    //Do not implement these methods in ProductServiceImplJdbc.java class
    default List<Product> getAllProductByWarehouse(int warehouseId) throws SQLException {
        return null;
    }
}
