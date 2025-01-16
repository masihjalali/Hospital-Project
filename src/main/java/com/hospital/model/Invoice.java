// Path: src/main/java/com/hospital/model/Invoice.java
package com.hospital.model;

import java.util.Date;
import java.util.List;

/**
 * Class representing an Invoice in the hospital management system.
 */
public class Invoice {

    // Attributes
    private String invoiceId;
    private Date dateIssued;
    private String patientId; // ID of the patient
    private List<String> serviceList; // List of service IDs
    private double totalAmount;
    private String roomId; // Room associated with the invoice, if applicable

    // Constructor
    public Invoice(String invoiceId, Date dateIssued, String patientId, List<String> serviceList, double totalAmount, String roomId) {
        this.invoiceId = invoiceId;
        this.dateIssued = dateIssued;
        this.patientId = patientId;
        this.serviceList = serviceList;
        this.totalAmount = totalAmount;
        this.roomId = roomId;
    }

    // Getters and Setters
    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Date getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(Date dateIssued) {
        this.dateIssued = dateIssued;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public List<String> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<String> serviceList) {
        this.serviceList = serviceList;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    // Calculate Total Amount based on services
    public void calculateTotalAmount(List<Double> serviceCosts) {
        this.totalAmount = serviceCosts.stream().mapToDouble(Double::doubleValue).sum();
    }

    // toString Method
    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId='" + invoiceId + '\'' +
                ", dateIssued=" + dateIssued +
                ", patientId='" + patientId + '\'' +
                ", serviceList=" + serviceList +
                ", totalAmount=" + totalAmount +
                ", roomId='" + roomId + '\'' +
                '}';
    }
}
