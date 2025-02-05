package com.wecp.progressive.service;

import com.wecp.progressive.entity.Shipment;

import java.sql.SQLException;
import java.util.List;

public interface ShipmentService {
    List<Shipment> getAllShipments() throws SQLException;

    Shipment getShipmentById(int shipmentId) throws SQLException;

    int addShipment(Shipment shipment) throws SQLException;

    void updateShipment(Shipment shipment) throws SQLException;

    void deleteShipment(int shipmentId) throws SQLException;

}
