package com.hospital.model;

import java.util.Date;

/**
 * Class representing an Appointment in the hospital management system.
 */
public class Appointment {

    // Attributes
    private String appointmentId; // Appointment ID
    private String patientId; // ID of the patient
    private String doctorId; // ID of the doctor
    private Date appointmentDate; // Date and time of the appointment
    private String status; // Status of the appointment (Scheduled, Completed, Cancelled)

    // Constructor
    public Appointment(String appointmentId, String patientId, String doctorId, Date appointmentDate, String status) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.status = status;
    }

    // Getters and Setters
    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString Method

}
