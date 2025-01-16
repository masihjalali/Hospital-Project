package com.hospital.model;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * Class representing an Invoice in the hospital management system.
 */
public class Invoice {

    // Attributes
    private String invoiceId; // Invoice ID
    private Date dateIssued; // Date when the invoice was issued
    private String patientId; // ID of the patient
    private List<String> serviceList; // List of service IDs
    private double totalAmount; // Total amount of the invoice
    private String roomId; // Room ID associated with the invoice

    // Constructor
    public Invoice(String invoiceId, Date dateIssued, String patientId, double totalAmount, String roomId) {
        this.invoiceId = invoiceId;
        this.dateIssued = dateIssued;
        this.patientId = patientId;
        this.totalAmount = totalAmount;
        this.roomId = roomId;
        this.serviceList = new ArrayList<>();
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

    public void addService(String serviceId) {
        this.serviceList.add(serviceId);
    }

    public void removeService(String serviceId) {
        this.serviceList.remove(serviceId);
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

    // toString Method
    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId='" + invoiceId + '\\'' +
                ", dateIssued=" + dateIssued +
                ", patientId='" + patientId + '\\'' +
                ", serviceList=" + serviceList +
                ", totalAmount=" + totalAmount +
                ", roomId='" + roomId + '\\'' +
                '}';
    }
}
