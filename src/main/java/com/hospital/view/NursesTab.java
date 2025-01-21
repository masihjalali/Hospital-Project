package com.hospital.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.hospital.model.Nurse;
import com.hospital.controller.HospitalController;

/**
 * Nurses Tab with Table and CRUD Operations
 */
public class NursesTab {

    private JTable table;
    private DefaultTableModel tableModel;
    private HospitalController controller;

    public NursesTab() {
        controller = new HospitalController();
    }

    public JPanel createNursesTab() {
        JPanel panel = new JPanel(new BorderLayout());

        // Table model setup
        String[] columnNames = {"Nurse ID", "Name", "Assigned Department", "Contact", "Assigned Patients"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Load data into the table
        // Load data into the table
        loadNursesData();

        // Add JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();

        // Add Button
        JButton addButton = new JButton("Add Nurse");
        addButton.addActionListener(e -> openAddNurseDialog());
        buttonPanel.add(addButton);

        // Edit Button
        JButton EditButton = new JButton("Edit Nurse");
        buttonPanel.add(EditButton);

        // Add to panel
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add Row Selection Listener
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    int row = table.getSelectedRow();
                    String nurseId = tableModel.getValueAt(row, 0).toString();
                    openEditNurseDialog(nurseId);
                }
            }
        });

        return panel;
    }

    private void loadNursesData() {
        tableModel.setRowCount(0); // Clear existing rows
        ArrayList <Nurse> nurses = controller.getAllNurses();
        for (Nurse nurse : nurses) {
            tableModel.addRow(new Object[]{
                    nurse.getNurseId(),
                    nurse.getName(),
                    nurse.getAssignedDepartment(),
                    nurse.getContactNumber(),
                    String.join(", ", nurse.getAssignedPatients())
            });
        }
    }

    private void openAddNurseDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Add Nurse");
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(6, 2));

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField departmentField = new JTextField();
        JTextField contactField = new JTextField();
        JTextField patientsField = new JTextField();

        dialog.add(new JLabel("Nurse ID:"));
        dialog.add(idField);
        dialog.add(new JLabel("Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Assigned Department:"));
        dialog.add(departmentField);
        dialog.add(new JLabel("Contact:"));
        dialog.add(contactField);
        dialog.add(new JLabel("Assigned Patients (comma-separated):"));
        dialog.add(patientsField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            Nurse nurse = new Nurse(
                    idField.getText(),
                    nameField.getText(),
                    departmentField.getText(),
                    contactField.getText()
            );
            String[] patients = patientsField.getText().split(",");
            for (String patient : patients) {
                nurse.addAssignedPatient(patient.trim());
            }
            controller.addNurse(nurse);
            loadNursesData();
            dialog.dispose();
        });
        dialog.add(saveButton);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private void openEditNurseDialog(String nurseId) {
        Nurse nurse = controller.getNurseById(nurseId);
        if (nurse == null) {
            JOptionPane.showMessageDialog(null, "Nurse not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog();
        dialog.setTitle("Edit Nurse");
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(6, 2));

        JTextField nameField = new JTextField(nurse.getName());
        JTextField departmentField = new JTextField(nurse.getAssignedDepartment());
        JTextField contactField = new JTextField(nurse.getContactNumber());
        JTextField patientsField = new JTextField(String.join(", ", nurse.getAssignedPatients()));

        dialog.add(new JLabel("Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Assigned Department:"));
        dialog.add(departmentField);
        dialog.add(new JLabel("Contact:"));
        dialog.add(contactField);
        dialog.add(new JLabel("Assigned Patients (comma-separated):"));
        dialog.add(patientsField);

        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(e -> {
            nurse.setName(nameField.getText());
            nurse.setAssignedDepartment(departmentField.getText());
            nurse.setContactNumber(contactField.getText());
            nurse.getAssignedPatients().clear();
            String[] patients = patientsField.getText().split(",");
            for (String patient : patients) {
                nurse.addAssignedPatient(patient.trim());
            }
            controller.addNurse(nurse);
            loadNursesData();
            dialog.dispose();
        });
        dialog.add(saveButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            controller.deleteNurseById(nurseId);
            loadNursesData();
            dialog.dispose();
        });
        dialog.add(deleteButton);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Nurses Tab Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new NursesTab().createNursesTab());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
