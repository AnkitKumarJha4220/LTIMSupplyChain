package com.wecp.progressive.service.impl;

import com.wecp.progressive.entity.Shipment;
import com.wecp.progressive.repository.InsuranceRepository;
import com.wecp.progressive.repository.ShipmentRepository;
import com.wecp.progressive.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    @Autowired
    ShipmentRepository shipmentRepository;

    @Autowired
    InsuranceRepository insuranceRepository;

    @Override
    public List<Shipment> getAllShipments() throws SQLException {
        return shipmentRepository.findAll();
    }

    @Override
    public Shipment getShipmentById(int shipmentId) throws SQLException {
        return shipmentRepository.findByShipmentId(shipmentId);
    }

    @Override
    public int addShipment(Shipment shipment) throws SQLException {
        return shipmentRepository.save(shipment).getShipmentId();
    }

    @Override
    public void updateShipment(Shipment shipment) throws SQLException {
        shipmentRepository.save(shipment);
    }

    @Override
    public void deleteShipment(int shipmentId) throws SQLException {
        insuranceRepository.deleteByShipmentId(shipmentId);
        shipmentRepository.deleteById(shipmentId);
    }
}