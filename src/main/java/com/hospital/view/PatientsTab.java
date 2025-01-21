package com.hospital.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import com.hospital.model.Patient;
import com.hospital.controller.HospitalController;

/**
 * Patients Tab with Table and CRUD Operations
 */
public class PatientsTab {

    private JTable table;
    private DefaultTableModel tableModel;
    private HospitalController controller;

    public PatientsTab() {
        controller = new HospitalController();
    }

    public JPanel createPatientsTab() {
        JPanel panel = new JPanel(new BorderLayout());

        // Table model setup
        String[] columnNames = {"ID", "Name", "Age", "Gender", "Contact", "Health Status", "Room", "Admission Date", "Discharge Date"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Load data into the table
        loadPatientsData();

        // Add JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();

        // Add Button
        JButton addButton = new JButton("Add Patient");
        addButton.addActionListener(e -> openAddPatientDialog());
        buttonPanel.add(addButton);

        // Edit Button
        JButton EditButton = new JButton("Edit Patients");
        buttonPanel.add(EditButton);

        // Add to panel
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add Row Selection Listener
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    int row = table.getSelectedRow();
                    String patientId = tableModel.getValueAt(row, 0).toString();
                    openEditPatientDialog(patientId);
                }
            }
        });

        return panel;
    }

    private void loadPatientsData() {
        tableModel.setRowCount(0); // Clear existing rows
        List<Patient> patients = controller.getAllPatients();
        for (Patient patient : patients) {
            tableModel.addRow(new Object[]{
                    patient.getId(),
                    patient.getName(),
                    patient.getAge(),
                    patient.getGender(),
                    patient.getContactNumber(),
                    patient.getHealthStatus(),
                    patient.getAllocatedRoom(),
                    patient.getAdmissionDate(),
                    patient.getDischargeDate()
            });
        }
    }

    private void openAddPatientDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Add Patient");
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(10, 2));

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField contactField = new JTextField();
        JTextField healthField = new JTextField();
        JTextField roomField = new JTextField();
        JTextField admissionField = new JTextField();
        JTextField dischargeField = new JTextField();

        dialog.add(new JLabel("ID:"));
        dialog.add(idField);
        dialog.add(new JLabel("Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Age:"));
        dialog.add(ageField);
        dialog.add(new JLabel("Gender:"));
        dialog.add(genderField);
        dialog.add(new JLabel("Contact:"));
        dialog.add(contactField);
        dialog.add(new JLabel("Health Status:"));
        dialog.add(healthField);
        dialog.add(new JLabel("Room:"));
        dialog.add(roomField);
        dialog.add(new JLabel("Admission Date:"));
        dialog.add(admissionField);
        dialog.add(new JLabel("Discharge Date:"));
        dialog.add(dischargeField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            Patient patient = new Patient(
                    idField.getText(),
                    nameField.getText(),
                    Integer.parseInt(ageField.getText()),
                    genderField.getText(),
                    contactField.getText(),
                    healthField.getText(),
                    roomField.getText(),
                    new Date(admissionField.getText()), // Adjust parsing
                    dischargeField.getText().isEmpty() ? null : new Date(dischargeField.getText()) // Adjust parsing
            );
            controller.addPatient(patient);
            loadPatientsData();
            dialog.dispose();
        });
        dialog.add(saveButton);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private void openEditPatientDialog(String patientId) {
        Patient patient = controller.getPatientById(patientId);
        if (patient == null) {
            JOptionPane.showMessageDialog(null, "Patient not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog();
        dialog.setTitle("Edit Patient");
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(10, 2));

        JTextField nameField = new JTextField(patient.getName());
        JTextField ageField = new JTextField(String.valueOf(patient.getAge()));
        JTextField genderField = new JTextField(patient.getGender());
        JTextField contactField = new JTextField(patient.getContactNumber());
        JTextField healthField = new JTextField(patient.getHealthStatus());
        JTextField roomField = new JTextField(patient.getAllocatedRoom());
        JTextField admissionField = new JTextField(patient.getAdmissionDate().toString());
        JTextField dischargeField = new JTextField(patient.getDischargeDate() != null ? patient.getDischargeDate().toString() : "");

        dialog.add(new JLabel("Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Age:"));
        dialog.add(ageField);
        dialog.add(new JLabel("Gender:"));
        dialog.add(genderField);
        dialog.add(new JLabel("Contact:"));
        dialog.add(contactField);
        dialog.add(new JLabel("Health Status:"));
        dialog.add(healthField);
        dialog.add(new JLabel("Room:"));
        dialog.add(roomField);
        dialog.add(new JLabel("Admission Date:"));
        dialog.add(admissionField);
        dialog.add(new JLabel("Discharge Date:"));
        dialog.add(dischargeField);

        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(e -> {
            patient.setName(nameField.getText());
            patient.setAge(Integer.parseInt(ageField.getText()));
            patient.setGender(genderField.getText());
            patient.setContactNumber(contactField.getText());
            patient.setHealthStatus(healthField.getText());
            patient.setAllocatedRoom(roomField.getText());
            // Adjust parsing for dates
            controller.addPatient(patient);
            loadPatientsData();
            dialog.dispose();
        });
        dialog.add(saveButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            controller.deletePatientById(patientId);
            loadPatientsData();
            dialog.dispose();
        });
        dialog.add(deleteButton);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Patients Tab Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new PatientsTab().createPatientsTab());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
