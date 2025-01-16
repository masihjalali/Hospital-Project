package com.hospital.model;

/**
 * Class representing a Support Staff member in the hospital management system.
 */
public class SupportStaff {

    // Attributes
    private String staffId; // Staff ID
    private String name; // Full name of the support staff member
    private String role; // Role (e.g., cleaner, technician, etc.)
    private String contactNumber; // Contact number of the staff member
    private String assignedSection; // Section the staff member is assigned to

    // Constructor
    public SupportStaff(String staffId, String name, String role, String contactNumber, String assignedSection) {
        this.staffId = staffId;
        this.name = name;
        this.role = role;
        this.contactNumber = contactNumber;
        this.assignedSection = assignedSection;
    }

    // Getters and Setters
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAssignedSection() {
        return assignedSection;
    }

    public void setAssignedSection(String assignedSection) {
        this.assignedSection = assignedSection;
    }

}
