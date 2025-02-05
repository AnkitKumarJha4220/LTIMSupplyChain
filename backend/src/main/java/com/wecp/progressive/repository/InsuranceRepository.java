package com.wecp.progressive.repository;

import com.wecp.progressive.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Integer> {

    Insurance findByInsuranceId(int insuranceId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Insurance ins WHERE ins.shipment.shipmentId = :shipmentId")
    void deleteByShipmentId(@Param("shipmentId") int shipmentId);
}
