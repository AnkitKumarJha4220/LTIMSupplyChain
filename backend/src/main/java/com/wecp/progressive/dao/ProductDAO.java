package com.wecp.progressive.dao;

import com.wecp.progressive.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {
    int addProduct(Product accounts) throws SQLException;
    Product getProductById(int productId) throws SQLException;
    void updateProduct(Product product) throws SQLException;
    void deleteProduct(int productId) throws SQLException;
    List<Product> getAllProducts() throws SQLException;
}