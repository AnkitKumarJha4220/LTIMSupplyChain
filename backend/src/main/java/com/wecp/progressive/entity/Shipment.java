package com.wecp.progressive.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shipmentId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="productId")
    private Product product;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="warehouseId")
    private Warehouse warehouse;

    private Date shipmentDate;

    private Date expectedDeliveryDate;

    private String sourceLocation;

    private String destinationLocation;

    private String status;

    public Shipment() {
    }

    public Shipment(int shipmentId, Product product, Warehouse warehouse, Date shipmentDate, Date expectedDeliveryDate, String sourceLocation, String destinationLocation, String status) {
        this.shipmentId = shipmentId;
        this.product = product;
        this.warehouse = warehouse;
        this.shipmentDate = shipmentDate;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.sourceLocation = sourceLocation;
        this.destinationLocation = destinationLocation;
        this.status = status;
    }

    public int getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(int shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public String getSourceLocation() {
        return sourceLocation;
    }

    public void setSourceLocation(String sourceLocation) {
        this.sourceLocation = sourceLocation;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}