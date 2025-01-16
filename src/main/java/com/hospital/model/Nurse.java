package com.hospital.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Class representing a Nurse in the hospital management system.
 */
public class Nurse {

    // Attributes
    private String nurseId; // Nurse ID
    private String name; // Full name of the nurse
    private String assignedDepartment; // Department the nurse is assigned to
    private String contactNumber; // Contact number of the nurse
    private List<String> assignedPatients; // List of patient IDs under the nurse's care

    // Constructor
    public Nurse(String nurseId, String name, String assignedDepartment, String contactNumber) {
        this.nurseId = nurseId;
        this.name = name;
        this.assignedDepartment = assignedDepartment;
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

    public String getAssignedDepartment() {
        return assignedDepartment;
    }

    public void setAssignedDepartment(String assignedDepartment) {
        this.assignedDepartment = assignedDepartment;
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

    public void addAssignedPatient(String patientId) {
        this.assignedPatients.add(patientId);
    }

    public void removeAssignedPatient(String patientId) {
        this.assignedPatients.remove(patientId);
    }


}
