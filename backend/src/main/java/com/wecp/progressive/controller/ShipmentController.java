package com.wecp.progressive.controller;

import com.wecp.progressive.entity.Shipment;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ShipmentController {

    public ResponseEntity<List<Shipment>> getAllShipments() {
        return null;
    }

    public ResponseEntity<Shipment> getShipmentById(int shipmentId) {
        return null;
    }

    public ResponseEntity<Integer> addShipment(Shipment shipment) {
        return null;
    }

    public ResponseEntity<Void> updateShipment(int shipmentId, Shipment shipment) {
        return null;
    }

    public ResponseEntity<Void> deleteShipment(int shipmentId) {
        return null;
    }
}
