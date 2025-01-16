package com.hospital.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a Service in the hospital management system.
 */
public class Service {

    // Attributes
    private String serviceId; // Service ID
    private String serviceName; // Name of the service
    private double cost; // Cost of the service
    private String description; // Description of the service
    private List<String> responsibleStaff; // List of responsible staff IDs

    // Constructor
    public Service(String serviceId, String serviceName, double cost, String description) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.cost = cost;
        this.description = description;
        this.responsibleStaff = new ArrayList<>();
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

    public List<String> getResponsibleStaff() {
        return responsibleStaff;
    }

    public void setResponsibleStaff(List<String> responsibleStaff) {
        this.responsibleStaff = responsibleStaff;
    }

    public void addResponsibleStaff(String staffId) {
        this.responsibleStaff.add(staffId);
    }

    public void removeResponsibleStaff(String staffId) {
        this.responsibleStaff.remove(staffId);
    }

}
