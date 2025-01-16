// Path: src/main/java/com/hospital/model/Doctor.java
package com.hospital.model;

import java.util.List;

/**
 * Class representing a Doctor in the hospital management system.
 */
public class Doctor {

    // Attributes
    private String doctorId;
    private String name;
    private String specialization; // Doctor's field of specialization
    private String contactNumber;
    private List<String> workSchedule; // List of working hours or shifts

    // Constructor
    public Doctor(String doctorId, String name, String specialization, String contactNumber, List<String> workSchedule) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.contactNumber = contactNumber;
        this.workSchedule = workSchedule;
    }

    // Getters and Setters
    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public List<String> getWorkSchedule() {
        return workSchedule;
    }

    public void setWorkSchedule(List<String> workSchedule) {
        this.workSchedule = workSchedule;
    }

    // toString Method
    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId='" + doctorId + '\'' +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", workSchedule=" + workSchedule +
                '}';
    }
}
