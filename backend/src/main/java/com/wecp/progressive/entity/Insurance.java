package com.wecp.progressive.entity;

import javax.persistence.*;

@Entity
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int insuranceId;

    @ManyToOne
    @JoinColumn(name = "shipmentId")
    private Shipment shipment;

    private String insuranceProvider;
    private Long insuranceCoverageAmount;

    public Insurance() {
    }

    public Insurance(int insuranceId, Shipment shipment, String insuranceProvider, Long insuranceCoverageAmount) {
        this.insuranceId = insuranceId;
        this.shipment = shipment;
        this.insuranceProvider = insuranceProvider;
        this.insuranceCoverageAmount = insuranceCoverageAmount;
    }

    public int getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(int insuranceId) {
        this.insuranceId = insuranceId;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public String getInsuranceProvider() {
        return insuranceProvider;
    }

    public void setInsuranceProvider(String insuranceProvider) {
        this.insuranceProvider = insuranceProvider;
    }

    public Long getInsuranceCoverageAmount() {
        return insuranceCoverageAmount;
    }

    public void setInsuranceCoverageAmount(Long insuranceCoverageAmount) {
        this.insuranceCoverageAmount = insuranceCoverageAmount;
    }
}