package com.wecp.progressive.dao;

import com.wecp.progressive.entity.Product;

import java.util.List;

public interface ProductDAO {
    int addProduct(Product product);
    Product getProductById(int productId);
    void updateProduct(Product product);
    void deleteProduct(int productId);
    List<Product> getAllProducts();
}