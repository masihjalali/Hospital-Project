// Path: src/main/java/com/hospital/model/Patient.java
package com.hospital.model;

import java.util.Date;

/**
 * Class representing a Patient in the hospital management system.
 */
public class Patient {

    // Attributes
    private String id;
    private String name;
    private int age;
    private String gender;
    private String contactNumber;
    private String healthStatus;
    private String allocatedRoom;
    private Date admissionDate;
    private Date dischargeDate;

    // Constructor
    public Patient(String id, String name, int age, String gender, String contactNumber, String healthStatus, String allocatedRoom, Date admissionDate, Date dischargeDate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.healthStatus = healthStatus;
        this.allocatedRoom = allocatedRoom;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getAllocatedRoom() {
        return allocatedRoom;
    }

    public void setAllocatedRoom(String allocatedRoom) {
        this.allocatedRoom = allocatedRoom;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    // toString Method
    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", healthStatus='" + healthStatus + '\'' +
                ", allocatedRoom='" + allocatedRoom + '\'' +
                ", admissionDate=" + admissionDate +
                ", dischargeDate=" + dischargeDate +
                '}';
    }
}
