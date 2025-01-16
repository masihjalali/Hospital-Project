package com.hospital.model;

/**
 * Class representing a Medication in the hospital management system.
 */
public class Medication {

    // Attributes
    private String medicationId; // Medication ID
    private String medicationName; // Name of the medication
    private String description; // Description of the medication
    private double cost; // Cost of the medication

    // Constructor
    public Medication(String medicationId, String medicationName, String description, double cost) {
        this.medicationId = medicationId;
        this.medicationName = medicationName;
        this.description = description;
        this.cost = cost;
    }

    // Getters and Setters
    public String getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(String medicationId) {
        this.medicationId = medicationId;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }


}
