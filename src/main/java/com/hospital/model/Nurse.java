// Path: src/main/java/com/hospital/model/Nurse.java
package com.hospital.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a Nurse in the hospital management system.
 */
public class Nurse {

    // Attributes
    private String nurseId;
    private String name;
    private String department; // Department the nurse is assigned to
    private String contactNumber;
    private List<String> assignedPatients; // List of Patient IDs under care

    // Constructor
    public Nurse(String nurseId, String name, String department, String contactNumber) {
        this.nurseId = nurseId;
        this.name = name;
        this.department = department;
        this.contactNumber = contactNumber;
        this.assignedPatients = new ArrayList<>();
    }

    // Getters and Setters
    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public List<String> getAssignedPatients() {
        return assignedPatients;
    }

    public void setAssignedPatients(List<String> assignedPatients) {
        this.assignedPatients = assignedPatients;
    }

    // Methods to manage assigned patients
    public void addPatient(String patientId) {
        this.assignedPatients.add(patientId);
    }

    public void removePatient(String patientId) {
        this.assignedPatients.remove(patientId);
    }

    // toString Method
    @Override
    public String toString() {
        return "Nurse{" +
                "nurseId='" + nurseId + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", assignedPatients=" + assignedPatients +
                '}';
    }
}
