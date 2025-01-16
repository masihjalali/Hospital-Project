package com.hospital.controller;

import java.sql.*;
import java.util.*;
import com.hospital.model.*; // Import all models

/**
 * Controller for managing all models in the hospital management system using MySQL database.
 */
public class HospitalController {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    // Constructor to initialize the database connection
    public HospitalController() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Connected to the database successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    // Methods for Patient
    public void addPatient(Patient patient) {
        String query = "INSERT INTO patients (id, name, age, gender, contactNumber, healthStatus, allocatedRoom, admissionDate, dischargeDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, patient.getId());
            stmt.setString(2, patient.getName());
            stmt.setInt(3, patient.getAge());
            stmt.setString(4, patient.getGender());
            stmt.setString(5, patient.getContactNumber());
            stmt.setString(6, patient.getHealthStatus());
            stmt.setString(7, patient.getAllocatedRoom());
            stmt.setDate(8, new java.sql.Date(patient.getAdmissionDate().getTime()));
            stmt.setDate(9, patient.getDischargeDate() != null ? new java.sql.Date(patient.getDischargeDate().getTime()) : null);
            stmt.executeUpdate();
            System.out.println("Patient added successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error adding patient", e);
        }
    }

    public List<Patient> getAllPatients() {
        String query = "SELECT * FROM patients";
        List<Patient> patients = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                patients.add(new Patient(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("contactNumber"),
                        rs.getString("healthStatus"),
                        rs.getString("allocatedRoom"),
                        rs.getDate("admissionDate"),
                        rs.getDate("dischargeDate")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving patients", e);
        }
        return patients;
    }

    public Patient getPatientById(String id) {
        String query = "SELECT * FROM patients WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Patient(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("contactNumber"),
                        rs.getString("healthStatus"),
                        rs.getString("allocatedRoom"),
                        rs.getDate("admissionDate"),
                        rs.getDate("dischargeDate")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving patient by ID", e);
        }
        return null;
    }

    public void deletePatientById(String id) {
        String query = "DELETE FROM patients WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("Patient deleted successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting patient", e);
        }
    }

    // Methods for Administrator
    public void addAdministrator(Administrator admin) {
        String query = "INSERT INTO administrators (id, name, username, password, contactNumber) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, admin.getId());
            stmt.setString(2, admin.getName());
            stmt.setString(3, admin.getUsername());
            stmt.setString(4, admin.getPassword());
            stmt.setString(5, admin.getContactNumber());
            stmt.executeUpdate();
            System.out.println("Administrator added successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error adding administrator", e);
        }
    }

    public List<Administrator> getAllAdministrators() {
        String query = "SELECT * FROM administrators";
        List<Administrator> admins = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                admins.add(new Administrator(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("contactNumber")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving administrators", e);
        }
        return admins;
    }

    public Administrator getAdministratorById(String id) {
        String query = "SELECT * FROM administrators WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Administrator(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("contactNumber")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving administrator by ID", e);
        }
        return null;
    }

    public void deleteAdministratorById(String id) {
        String query = "DELETE FROM administrators WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("Administrator deleted successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting administrator", e);
        }
    }

    // Methods for Medication
    public void addMedication(Medication medication) {
        String query = "INSERT INTO medications (id, name, description, cost) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, medication.getMedicationId());
            stmt.setString(2, medication.getMedicationName());
            stmt.setString(3, medication.getDescription());
            stmt.setDouble(4, medication.getCost());
            stmt.executeUpdate();
            System.out.println("Medication added successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error adding medication", e);
        }
    }

    public List<Medication> getAllMedications() {
        String query = "SELECT * FROM medications";
        List<Medication> medications = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                medications.add(new Medication(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("cost")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving medications", e);
        }
        return medications;
    }

    public Medication getMedicationById(String id) {
        String query = "SELECT * FROM medications WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Medication(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("cost")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving medication by ID", e);
        }
        return null;
    }

    public void deleteMedicationById(String id) {
        String query = "DELETE FROM medications WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("Medication deleted successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting medication", e);
        }
    }

    // Add similar methods for other models like Appointment, Doctor, Invoice, Medication, Nurse, Room, Service, SupportStaff

}
