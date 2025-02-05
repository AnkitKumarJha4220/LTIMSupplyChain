package com.wecp.progressive.repository;

import com.wecp.progressive.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByProductId(@Param("productId") int productId);

    List<Product> findAllByWarehouse_WarehouseId(@Param("warehouseId") int warehouseId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Product p WHERE p.warehouse.warehouseId = :warehouseId")
    void deleteByWarehouseId(@Param("warehouseId") int warehouseId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Product p WHERE p.warehouse.warehouseId in (Select w.warehouseId from Warehouse w where w.supplier.supplierId = :supplierId)")
    void deleteBySupplierId(@Param("supplierId") int supplierId);

    int countByWarehouse_WarehouseId(Integer warehouseId);
}
