package com.hospital.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.hospital.model.Appointment;
import com.hospital.controller.HospitalController;

/**
 * Appointments Tab with Table and CRUD Operations
 */
public class AppointmentsTab {

    private JTable table;
    private DefaultTableModel tableModel;
    private HospitalController controller;

    public AppointmentsTab() {
        controller = new HospitalController();
    }

    public JPanel createAppointmentsTab() {
        JPanel panel = new JPanel(new BorderLayout());

        // Table model setup
        String[] columnNames = {"Appointment ID", "Patient ID", "Doctor ID", "Date & Time", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Load data into the table
        loadAppointmentsData();

        // Add JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();

        // Add Button
        JButton addButton = new JButton("Add Appointment");
        addButton.addActionListener(e -> openAddAppointmentDialog());
        buttonPanel.add(addButton);

        // Add to panel
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add Row Selection Listener
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    int row = table.getSelectedRow();
                    String appointmentId = tableModel.getValueAt(row, 0).toString();
                    openEditAppointmentDialog(appointmentId);
                }
            }
        });

        return panel;
    }

    private void loadAppointmentsData() {
        tableModel.setRowCount(0); // Clear existing rows
        ArrayList<Appointment> appointments = controller.getAllAppointments();
        for (Appointment appointment : appointments) {
            tableModel.addRow(new Object[]{
                    appointment.getAppointmentId(),
                    appointment.getPatientId(),
                    appointment.getDoctorId(),
                    appointment.getAppointmentDate(),
                    appointment.getStatus()
            });
        }
    }

    private void openAddAppointmentDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Add Appointment");
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(6, 2));

        JTextField idField = new JTextField();
        JTextField patientIdField = new JTextField();
        JTextField doctorIdField = new JTextField();
        JTextField dateTimeField = new JTextField();
        JTextField statusField = new JTextField();

        dialog.add(new JLabel("Appointment ID:"));
        dialog.add(idField);
        dialog.add(new JLabel("Patient ID:"));
        dialog.add(patientIdField);
        dialog.add(new JLabel("Doctor ID:"));
        dialog.add(doctorIdField);
        dialog.add(new JLabel("Date & Time:"));
        dialog.add(dateTimeField);
        dialog.add(new JLabel("Status:"));
        dialog.add(statusField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            Appointment appointment = new Appointment(
                    idField.getText(),
                    patientIdField.getText(),
                    doctorIdField.getText(),
                    new Date(dateTimeField.getText()),
                    statusField.getText()
            );
            controller.addAppointment(appointment);
            loadAppointmentsData();
            dialog.dispose();
        });
        dialog.add(saveButton);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private void openEditAppointmentDialog(String appointmentId) {
        Appointment appointment = controller.getAppointmentById(appointmentId);
        if (appointment == null) {
            JOptionPane.showMessageDialog(null, "Appointment not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog();
        dialog.setTitle("Edit Appointment");
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(6, 2));

        JTextField patientIdField = new JTextField(appointment.getPatientId());
        JTextField doctorIdField = new JTextField(appointment.getDoctorId());
        JTextField dateTimeField = new JTextField(appointment.getAppointmentDate().toString());
        JTextField statusField = new JTextField(appointment.getStatus());

        dialog.add(new JLabel("Patient ID:"));
        dialog.add(patientIdField);
        dialog.add(new JLabel("Doctor ID:"));
        dialog.add(doctorIdField);
        dialog.add(new JLabel("Date & Time:"));
        dialog.add(dateTimeField);
        dialog.add(new JLabel("Status:"));
        dialog.add(statusField);

        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(e -> {
            appointment.setPatientId(patientIdField.getText());
            appointment.setDoctorId(doctorIdField.getText());
            appointment.setAppointmentDate(new Date(dateTimeField.getText()));
            appointment.setStatus(statusField.getText());
            controller.addAppointment(appointment);
            loadAppointmentsData();
            dialog.dispose();
        });
        dialog.add(saveButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            controller.deleteAppointmentById(appointmentId);
            loadAppointmentsData();
            dialog.dispose();
        });
        dialog.add(deleteButton);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Appointments Tab Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new AppointmentsTab().createAppointmentsTab());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
