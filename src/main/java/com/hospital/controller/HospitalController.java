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

    public ArrayList<Administrator> getAllAdministrators() {
        String query = "SELECT * FROM administrators";
        ArrayList<Administrator> admins = new ArrayList<>();
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

    public ArrayList<Medication> getAllMedications() {
        String query = "SELECT * FROM medications";
        ArrayList<Medication> medications = new ArrayList<>();
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

    // Add a new service
    public void addService(Service service) {
        String query = "INSERT INTO services (id, name, cost, description, responsible_staff) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, service.getServiceId());
            stmt.setString(2, service.getServiceName());
            stmt.setDouble(3, service.getCost());
            stmt.setString(4, service.getDescription());
            stmt.setString(5, String.join(",", service.getResponsibleStaff()));

            stmt.executeUpdate();
            System.out.println("Service added successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error adding service", e);
        }
    }

    // Retrieve all services
    public ArrayList<Service> getAllServices() {
        ArrayList<Service> services = new ArrayList<>();
        String query = "SELECT * FROM services";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Service service = new Service(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getDouble("cost"),
                        rs.getString("description")
                );
                String[] staff = rs.getString("responsible_staff").split(",");
                for (String member : staff) {
                    service.addResponsibleStaff(member);
                }
                services.add(service);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving services", e);
        }
        return services;
    }

    // Retrieve a service by ID
    public Service getServiceById(String id) {
        String query = "SELECT * FROM services WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Service service = new Service(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getDouble("cost"),
                        rs.getString("description")
                );
                String[] staff = rs.getString("responsible_staff").split(",");
                for (String member : staff) {
                    service.addResponsibleStaff(member);
                }
                return service;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving service by ID", e);
        }
        return null;
    }

    // Update a service
    public void updateService(Service service) {
        String query = "UPDATE services SET name = ?, cost = ?, description = ?, responsible_staff = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, service.getServiceName());
            stmt.setDouble(2, service.getCost());
            stmt.setString(3, service.getDescription());
            stmt.setString(4, String.join(",", service.getResponsibleStaff()));
            stmt.setString(5, service.getServiceId());

            stmt.executeUpdate();
            System.out.println("Service updated successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error updating service", e);
        }
    }

    // Delete a service by ID
    public void deleteServiceById(String id) {
        String query = "DELETE FROM services WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("Service deleted successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting service", e);
        }
    }

    // Add a new nurse
    public void addNurse(Nurse nurse) {
        String query = "INSERT INTO nurses (id, name, assigned_department, contact, assigned_patients) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, nurse.getNurseId());
            stmt.setString(2, nurse.getName());
            stmt.setString(3, nurse.getAssignedDepartment());
            stmt.setString(4, nurse.getContactNumber());
            stmt.setString(5, String.join(",", nurse.getAssignedPatients()));

            stmt.executeUpdate();
            System.out.println("Nurse added successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error adding nurse", e);
        }
    }

    // Retrieve all nurses
    public ArrayList<Nurse> getAllNurses() {
        ArrayList<Nurse> nurses = new ArrayList<>();
        String query = "SELECT * FROM nurses";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Nurse nurse = new Nurse(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("assigned_department"),
                        rs.getString("contact")
                );
                String[] patients = rs.getString("assigned_patients").split(",");
                for (String patient : patients) {
                    nurse.addAssignedPatient(patient);
                }
                nurses.add(nurse);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving nurses", e);
        }
        return nurses;
    }

    // Retrieve a nurse by ID
    public Nurse getNurseById(String id) {
        String query = "SELECT * FROM nurses WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Nurse nurse = new Nurse(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("assigned_department"),
                        rs.getString("contact")
                );
                String[] patients = rs.getString("assigned_patients").split(",");
                for (String patient : patients) {
                    nurse.addAssignedPatient(patient);
                }
                return nurse;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving nurse by ID", e);
        }
        return null;
    }

    // Update a nurse
    public void updateNurse(Nurse nurse) {
        String query = "UPDATE nurses SET name = ?, assigned_department = ?, contact = ?, assigned_patients = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, nurse.getName());
            stmt.setString(2, nurse.getAssignedDepartment());
            stmt.setString(3, nurse.getContactNumber());
            stmt.setString(4, String.join(",", nurse.getAssignedPatients()));
            stmt.setString(5, nurse.getNurseId());

            stmt.executeUpdate();
            System.out.println("Nurse updated successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error updating nurse", e);
        }
    }

    // Delete a nurse by ID
    public void deleteNurseById(String id) {
        String query = "DELETE FROM nurses WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("Nurse deleted successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting nurse", e);
        }
    }
    // Add a new support staff
    public void addSupportStaff(SupportStaff staff) {
        String query = "INSERT INTO support_staff (id, name, role, contact, assigned_section) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, staff.getStaffId());
            stmt.setString(2, staff.getName());
            stmt.setString(3, staff.getRole());
            stmt.setString(4, staff.getContactNumber());
            stmt.setString(5, staff.getAssignedSection());

            stmt.executeUpdate();
            System.out.println("Support staff added successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error adding support staff", e);
        }
    }

    // Retrieve all support staff
    public ArrayList<SupportStaff> getAllSupportStaff() {
        ArrayList<SupportStaff> staffList = new ArrayList<>();
        String query = "SELECT * FROM support_staff";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                SupportStaff staff = new SupportStaff(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getString("contact"),
                        rs.getString("assigned_section")
                );
                staffList.add(staff);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving support staff", e);
        }
        return staffList;
    }

    // Retrieve a support staff by ID
    public SupportStaff getSupportStaffById(String id) {
        String query = "SELECT * FROM support_staff WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new SupportStaff(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getString("contact"),
                        rs.getString("assigned_section")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving support staff by ID", e);
        }
        return null;
    }

    // Update a support staff
    public void updateSupportStaff(SupportStaff staff) {
        String query = "UPDATE support_staff SET name = ?, role = ?, contact = ?, assigned_section = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, staff.getName());
            stmt.setString(2, staff.getRole());
            stmt.setString(3, staff.getContactNumber());
            stmt.setString(4, staff.getAssignedSection());
            stmt.setString(5, staff.getStaffId());

            stmt.executeUpdate();
            System.out.println("Support staff updated successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error updating support staff", e);
        }
    }

    // Delete a support staff by ID
    public void deleteSupportStaffById(String id) {
        String query = "DELETE FROM support_staff WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("Support staff deleted successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting support staff", e);
        }
    }
    // Add similar methods for other models like Appointment, Doctor, Invoice, Medication, Nurse, Room, Service, SupportStaff

}
