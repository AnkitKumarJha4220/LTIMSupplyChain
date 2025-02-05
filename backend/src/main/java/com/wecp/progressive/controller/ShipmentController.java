package com.wecp.progressive.controller;

import com.wecp.progressive.entity.Shipment;
import com.wecp.progressive.service.impl.ShipmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/shipment")
public class ShipmentController {

    @Autowired
    ShipmentServiceImpl shipmentService;

    @GetMapping
    public ResponseEntity<List<Shipment>> getAllShipments() throws SQLException {
        List<Shipment> shipments = shipmentService.getAllShipments();
        return new ResponseEntity<>(shipments, HttpStatus.OK);
    }

    @GetMapping("/{shipmentId}")
    public ResponseEntity<Shipment> getShipmentById(@PathVariable int shipmentId) {
        try {
            Shipment shipment = shipmentService.getShipmentById(shipmentId);
            if (shipment != null) {
                return new ResponseEntity<>(shipment, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Integer> addShipment(@RequestBody Shipment shipment) {
        try {
            int shipmentId = shipmentService.addShipment(shipment);
            return new ResponseEntity<>(shipmentId, HttpStatus.CREATED);
        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{shipmentId}")
    public ResponseEntity<Void> updateShipment(@PathVariable int shipmentId, @RequestBody Shipment shipment) {
        try {
            shipment.setShipmentId(shipmentId);
            shipmentService.updateShipment(shipment);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{shipmentId}")
    public ResponseEntity<Void> deleteShipment(@PathVariable int shipmentId) {
        try {
            shipmentService.deleteShipment(shipmentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
