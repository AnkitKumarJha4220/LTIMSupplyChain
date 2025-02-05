package com.wecp.progressive.service.impl;

import com.wecp.progressive.entity.Product;
import com.wecp.progressive.entity.Warehouse;
import com.wecp.progressive.exception.InsufficientCapacityException;
import com.wecp.progressive.repository.ProductRepository;
import com.wecp.progressive.repository.ShipmentRepository;
import com.wecp.progressive.repository.WarehouseRepository;
import com.wecp.progressive.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProductServiceImplJpa implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    ShipmentRepository shipmentRepository;

    @Autowired
    public ProductServiceImplJpa(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() throws SQLException {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int productId) throws SQLException {
        return productRepository.findByProductId(productId);
    }

    @Override
    public int addProduct(Product product) throws InsufficientCapacityException {
        Warehouse warehouse = warehouseRepository.findByWarehouseId(product.getWarehouse().getWarehouseId());
        int productCount = productRepository.countByWarehouse_WarehouseId(warehouse.getWarehouseId());
        if (warehouse.getCapacity() == productCount) {
            throw new InsufficientCapacityException(
                    "Warehouse with ID " + warehouse.getWarehouseId() + " has reached its maximum capacity of " + warehouse.getCapacity() + " products."
            );
        }
        return productRepository.save(product).getProductId();
    }

    @Override
    public void updateProduct(Product product) throws SQLException {
        productRepository.save(product).getProductId();
    }

    @Override
    public void deleteProduct(int productId) throws SQLException {
        shipmentRepository.deleteByProductId(productId);
        productRepository.deleteById(productId);
    }

    @Override
    public List<Product> getAllProductByWarehouse(int warehouseId) throws SQLException {
        return productRepository.findAllByWarehouse_WarehouseId(warehouseId);
    }
}