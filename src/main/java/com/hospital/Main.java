package com.hospital;

import com.hospital.model.*;
import java.util.*;
import java.util.Date;


/**
 * Main class for testing all models.
 */
public class Main {
    public static void main(String[] args) {

        // Testing Patient Model
        Patient patient = new Patient("P001", "John Doe", 30, "Male", "1234567890", "Healthy", "101", new Date(), null);
        System.out.println("Patient: " + patient);

        // Testing Administrator Model
        Administrator admin = new Administrator("A001", "Jane Smith", "admin", "password123", "9876543210");
        System.out.println("Administrator: " + admin);

        // Testing Medication Model
        Medication medication = new Medication("M001", "Paracetamol", "Pain reliever", 10.0);
        System.out.println("Medication: " + medication);

        // Testing Doctor Model
        Doctor doctor = new Doctor("D001", "Dr. Emily Brown", "Cardiology", "1122334455",new ArrayList<>());
        System.out.println("Doctor: " + doctor);

        // Testing Nurse Model
        Nurse nurse = new Nurse("N001", "Nina Green", "Emergency", "9988776655");
        nurse.addAssignedPatient("P001");
        System.out.println("Nurse: " + nurse);

        // Testing Room Model
        Room room = new Room("R101", "101", 2, "Private", "Vacant");
        room.addPatient("P001");
        System.out.println("Room: " + room);

        // Testing Service Model
        Service service = new Service("S001", "MRI Scan", 300.0, "Detailed imaging of the body");
        service.addResponsibleStaff("D001");
        System.out.println("Service: " + service);

        // Testing Appointment Model
        Appointment appointment = new Appointment("AP001", "P001", "D001", new Date(), "Scheduled");
        System.out.println("Appointment: " + appointment);

        // Testing SupportStaff Model
        SupportStaff staff = new SupportStaff("SS001", "Tom Worker", "Cleaner", "4433221100", "ICU");
        System.out.println("Support Staff: " + staff);

        // Testing Invoice Model
        Invoice invoice = new Invoice("INV001", new Date(), "P001", 300.0, "R101");
        invoice.addService("S001");
        System.out.println("Invoice: " + invoice);

        System.out.println("All models tested successfully.");
        }
    }
