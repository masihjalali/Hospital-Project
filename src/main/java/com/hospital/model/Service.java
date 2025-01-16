// Path: src/main/java/com/hospital/model/Service.java
package com.hospital.model;

/**
 * Class representing a Service in the hospital management system.
 */
public class Service {

    // Attributes
    private String serviceId;
    private String serviceName;
    private double cost;
    private String description;
    private String responsibleStaff; // ID of the staff responsible for the service

    // Constructor
    public Service(String serviceId, String serviceName, double cost, String description, String responsibleStaff) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.cost = cost;
        this.description = description;
        this.responsibleStaff = responsibleStaff;
    }

    // Getters and Setters
    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponsibleStaff() {
        return responsibleStaff;
    }

    public void setResponsibleStaff(String responsibleStaff) {
        this.responsibleStaff = responsibleStaff;
    }

    // toString Method
    @Override
    public String toString() {
        return "Service{" +
                "serviceId='" + serviceId + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                ", responsibleStaff='" + responsibleStaff + '\'' +
                '}';
    }
}
