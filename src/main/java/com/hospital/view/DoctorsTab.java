package com.hospital.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import com.hospital.model.Doctor;
import com.hospital.controller.HospitalController;

/**
 * Doctors Tab with Table and CRUD Operations
 */
public class DoctorsTab {

    private JTable table;
    private DefaultTableModel tableModel;
    private HospitalController controller;

    public DoctorsTab() {
        controller = new HospitalController();
    }

    public JPanel createDoctorsTab() {
        JPanel panel = new JPanel(new BorderLayout());

        // Table model setup
        String[] columnNames = {"ID", "Name", "Specialization", "Contact", "Work Schedule"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Load data into the table
        loadDoctorsData();

        // Add JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();

        // Add Button
        JButton addButton = new JButton("Add Doctor");
        addButton.addActionListener(e -> openAddDoctorDialog());
        buttonPanel.add(addButton);

        // Add to panel
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add Row Selection Listener
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    int row = table.getSelectedRow();
                    String doctorId = tableModel.getValueAt(row, 0).toString();
                    openEditDoctorDialog(doctorId);
                }
            }
        });

        return panel;
    }

    private void loadDoctorsData() {
        tableModel.setRowCount(0); // Clear existing rows
//        ArrayList<Doctor> doctors = controller.getAllDoctors();
        ArrayList<Doctor> doctors = new ArrayList<>();
        for (Doctor doctor : doctors) {
            tableModel.addRow(new Object[]{
                    doctor.getDoctorId(),
                    doctor.getName(),
                    doctor.getSpecialization(),
                    doctor.getContactNumber(),
                    String.join(", ", doctor.getWorkSchedule())
            });
        }
    }

    private void openAddDoctorDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Add Doctor");
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(6, 2));

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField specializationField = new JTextField();
        JTextField contactField = new JTextField();
        JTextField workScheduleField = new JTextField();

        dialog.add(new JLabel("ID:"));
        dialog.add(idField);
        dialog.add(new JLabel("Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Specialization:"));
        dialog.add(specializationField);
        dialog.add(new JLabel("Contact:"));
        dialog.add(contactField);
        dialog.add(new JLabel("Work Schedule (comma-separated):"));
        dialog.add(workScheduleField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            Doctor doctor = new Doctor(
                    idField.getText(),
                    nameField.getText(),
                    specializationField.getText(),
                    contactField.getText(),new ArrayList<>()
            );
            String[] schedules = workScheduleField.getText().split(",");
            for (String schedule : schedules) {
//                doctor.addWorkSchedule(schedule.trim());
            }
//            controller.addDoctor(doctor);
            loadDoctorsData();
            dialog.dispose();
        });
        dialog.add(saveButton);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private void openEditDoctorDialog(String doctorId) {
//        Doctor doctor = controller.getDoctorById(doctorId);
        Doctor doctor = null;
        if (doctor == null) {
            JOptionPane.showMessageDialog(null, "Doctor not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog();
        dialog.setTitle("Edit Doctor");
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(6, 2));

        JTextField nameField = new JTextField(doctor.getName());
        JTextField specializationField = new JTextField(doctor.getSpecialization());
        JTextField contactField = new JTextField(doctor.getContactNumber());
        JTextField workScheduleField = new JTextField(String.join(", ", doctor.getWorkSchedule()));

        dialog.add(new JLabel("Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Specialization:"));
        dialog.add(specializationField);
        dialog.add(new JLabel("Contact:"));
        dialog.add(contactField);
        dialog.add(new JLabel("Work Schedule (comma-separated):"));
        dialog.add(workScheduleField);

        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(e -> {
            doctor.setName(nameField.getText());
            doctor.setSpecialization(specializationField.getText());
            doctor.setContactNumber(contactField.getText());
            doctor.getWorkSchedule().clear();
            String[] schedules = workScheduleField.getText().split(",");
            for (String schedule : schedules) {
//                doctor.addWorkSchedule(schedule.trim());
            }
//            controller.addDoctor(doctor);
            loadDoctorsData();
            dialog.dispose();
        });
        dialog.add(saveButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
//            controller.deleteDoctorById(doctorId);
            loadDoctorsData();
            dialog.dispose();
        });
        dialog.add(deleteButton);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Doctors Tab Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new DoctorsTab().createDoctorsTab());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
