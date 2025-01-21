package com.hospital.controller;

import java.sql.*;
import java.util.*;
import com.hospital.model.*; // Import all models
import com.hospital.view.*; // Import all models

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Controller for managing all models in the hospital management system using MySQL database.
 */
public class HospitalController {

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/hospital_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    // Constructor to initialize the database connection
    public HospitalController() {
        try (
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Connected to the database successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error Connecting to the database", e);
        }
    }
    // Methods for Patient
    public void addPatient(Patient patient) {
        String query = "INSERT INTO patients (id, name, age, gender, contact_number, health_status, allocated_room, admission_date, discharge_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
                        rs.getString("contact_number"),
                        rs.getString("health_status"),
                        rs.getString("allocated_room"),
                        rs.getDate("admission_date"),
                        rs.getDate("discharge_date")
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
                        rs.getString("contact_number"),
                        rs.getString("health_status"),
                        rs.getString("allocated_room"),
                        rs.getDate("admission_date"),
                        rs.getDate("discharge_date")
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
        String query = "INSERT INTO administrators (id, name, username, password, contact_number) VALUES (?, ?, ?, ?, ?)";
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
                        rs.getString("contact_number")
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
                        rs.getString("contact_number")
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
        String query = "INSERT INTO nurses (id, name, assigned_department, contact_number, assigned_patients) VALUES (?, ?, ?, ?, ?)";
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
                        rs.getString("contact_number")
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
                        rs.getString("contact_number")
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
        String query = "UPDATE nurses SET name = ?, assigned_department = ?, contact_number = ?, assigned_patients = ? WHERE id = ?";
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
        String query = "INSERT INTO support_staff (id, name, role, contact_number, assigned_section) VALUES (?, ?, ?, ?, ?)";
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
                        rs.getString("contact_number"),
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
                        rs.getString("contact_number"),
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
        String query = "UPDATE support_staff SET name = ?, role = ?, contact_number = ?, assigned_section = ? WHERE id = ?";
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

    // Add a new appointment
    public void addAppointment(Appointment appointment) {
        String query = "INSERT INTO appointments (id, patient_id, doctor_id, date_time, status_) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, appointment.getAppointmentId());
            stmt.setString(2, appointment.getPatientId());
            stmt.setString(3, appointment.getDoctorId());
            stmt.setString(4, appointment.getAppointmentId());
            stmt.setString(5, appointment.getStatus());

            stmt.executeUpdate();
            System.out.println("Appointment added successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error adding appointment", e);
        }
    }

    // Retrieve all appointments
    public ArrayList<Appointment> getAllAppointments() {
        ArrayList<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM appointments";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Appointment appointment = new Appointment(
                        rs.getString("id"),
                        rs.getString("patient_id"),
                        rs.getString("doctor_id"),
                        rs.getDate("date_time"),
                        rs.getString("status_")
                );
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving appointments", e);
        }
        return appointments;
    }

    // Retrieve an appointment by ID
    public Appointment getAppointmentById(String id) {
        String query = "SELECT * FROM appointments WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Appointment(
                        rs.getString("id"),
                        rs.getString("patient_id"),
                        rs.getString("doctor_id"),
                        rs.getDate("date_time"),
                        rs.getString("status_")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving appointment by ID", e);
        }
        return null;
    }

    // Update an appointment
    public void updateAppointment(Appointment appointment) {
        String query = "UPDATE appointments SET patient_id = ?, doctor_id = ?, date_time = ?, status_ = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, appointment.getPatientId());
            stmt.setString(2, appointment.getDoctorId());
            stmt.setString(3, appointment.getAppointmentId());
            stmt.setString(4, appointment.getStatus());
            stmt.setString(5, appointment.getAppointmentId());

            stmt.executeUpdate();
            System.out.println("Appointment updated successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error updating appointment", e);
        }
    }

    // Delete an appointment by ID
    public void deleteAppointmentById(String id) {
        String query = "DELETE FROM appointments WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("Appointment deleted successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting appointment", e);
        }
        // Add similar methods for other models like Appointment, Doctor, Invoice, Medication, Nurse, Room, Service, SupportStaff
    }

    // Add a new doctor
    public void addDoctor(Doctor doctor) {
        String query = "INSERT INTO doctors (id, name, specialization, contact_number, work_schedule) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, doctor.getDoctorId());
            stmt.setString(2, doctor.getName());
            stmt.setString(3, doctor.getSpecialization());
            stmt.setString(4, doctor.getContactNumber());
            stmt.setString(5, doctor.getWorkSchedule().toString());

            stmt.executeUpdate();
            System.out.println("Doctor added successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error adding doctor", e);
        }
    }

    // Retrieve all doctors
    public ArrayList<Doctor> getAllDoctors() {
        ArrayList<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctors";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Doctor doctor = new Doctor(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("specialization"),
                        rs.getString("contact_number"),
                        Collections.singletonList(rs.getString("work_schedule"))
                );
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving doctors", e);
        }
        return doctors;
    }

    // Retrieve a doctor by ID
    public Doctor getDoctorById(String id) {
        String query = "SELECT * FROM doctors WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Doctor(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("specialization"),
                        rs.getString("contact_number"),
                        Collections.singletonList(rs.getString("work_schedule"))
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving doctor by ID", e);
        }
        return null;
    }

    // Update a doctor
    public void updateDoctor(Doctor doctor) {
        String query = "UPDATE doctors SET name = ?, specialization = ?, contact_number = ?, work_schedule = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getSpecialization());
            stmt.setString(3, doctor.getContactNumber());
            stmt.setString(4, doctor.getWorkSchedule().toString());
            stmt.setString(5, doctor.getDoctorId());

            stmt.executeUpdate();
            System.out.println("Doctor updated successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error updating doctor", e);
        }
    }

    // Delete a doctor by ID
    public void deleteDoctorById(String id) {
        String query = "DELETE FROM doctors WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("Doctor deleted successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting doctor", e);
        }
    }

    // Add a new room
    public void addRoom(Room room) {
        String query = "INSERT INTO rooms (id, room_number, capacity, room_type, status, patients_in_room) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, room.getRoomId());
            stmt.setString(2, room.getRoomNumber());
            stmt.setInt(3, room.getCapacity());
            stmt.setString(4, room.getRoomType());
            stmt.setString(5, room.getStatus());
            stmt.setString(6, String.join(",", room.getPatientsInRoom()));

            stmt.executeUpdate();
            System.out.println("Room added successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error adding room", e);
        }
    }

    // Retrieve all rooms
    public ArrayList<Room> getAllRooms() {
        ArrayList<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM rooms";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Room room = new Room(
                        rs.getString("id"),
                        rs.getString("room_number"),
                        rs.getInt("capacity"),
                        rs.getString("room_type"),
                        rs.getString("status")
                );
                String[] patients = rs.getString("patients_in_room").split(",");
                for (String patient : patients) {
                    room.addPatient(patient);
                }
                rooms.add(room);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving rooms", e);
        }
        return rooms;
    }

    // Retrieve a room by ID
    public Room getRoomById(String id) {
        String query = "SELECT * FROM rooms WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Room room = new Room(
                        rs.getString("id"),
                        rs.getString("room_number"),
                        rs.getInt("capacity"),
                        rs.getString("room_type"),
                        rs.getString("status")
                );
                String[] patients = rs.getString("patients_in_room").split(",");
                for (String patient : patients) {
                    room.addPatient(patient);
                }
                return room;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving room by ID", e);
        }
        return null;
    }

    // Update a room
    public void updateRoom(Room room) {
        String query = "UPDATE rooms SET room_number = ?, capacity = ?, room_type = ?, status = ?, patients_in_room = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, room.getRoomNumber());
            stmt.setInt(2, room.getCapacity());
            stmt.setString(3, room.getRoomType());
            stmt.setString(4, room.getStatus());
            stmt.setString(5, String.join(",", room.getPatientsInRoom()));
            stmt.setString(6, room.getRoomId());

            stmt.executeUpdate();
            System.out.println("Room updated successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error updating room", e);
        }
    }

    // Delete a room by ID
    public void deleteRoomById(String id) {
        String query = "DELETE FROM rooms WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("Room deleted successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting room", e);
        }
    }

}
