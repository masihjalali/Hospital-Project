package com.hospital.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.hospital.model.Medication;
import com.hospital.controller.HospitalController;

/**
 * Medications Tab with Table and CRUD Operations
 */
public class MedicationsTab {

    private JTable table;
    private DefaultTableModel tableModel;
    private HospitalController controller;

    public MedicationsTab() {
        controller = new HospitalController();
    }

    public JPanel createMedicationsTab() {
        JPanel panel = new JPanel(new BorderLayout());

        // Table model setup
        String[] columnNames = {"Medication ID", "Name", "Description", "Cost"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Load data into the table
        loadMedicationsData();

        // Add JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();

        // Add Button
        JButton addButton = new JButton("Add Medication");
        addButton.addActionListener(e -> openAddMedicationDialog());
        buttonPanel.add(addButton);

        // Add to panel
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add Row Selection Listener
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    int row = table.getSelectedRow();
                    String medicationId = tableModel.getValueAt(row, 0).toString();
                    openEditMedicationDialog(medicationId);
                }
            }
        });

        return panel;
    }

    private void loadMedicationsData() {
        tableModel.setRowCount(0); // Clear existing rows
        List<Medication> medications = controller.getAllMedications();
        for (Medication medication : medications) {
            tableModel.addRow(new Object[]{
                    medication.getMedicationId(),
                    medication.getMedicationName(),
                    medication.getDescription(),
                    medication.getCost()
            });
        }
    }

    private void openAddMedicationDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Add Medication");
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(5, 2));

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField costField = new JTextField();

        dialog.add(new JLabel("Medication ID:"));
        dialog.add(idField);
        dialog.add(new JLabel("Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Description:"));
        dialog.add(descriptionField);
        dialog.add(new JLabel("Cost:"));
        dialog.add(costField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            Medication medication = new Medication(
                    idField.getText(),
                    nameField.getText(),
                    descriptionField.getText(),
                    Double.parseDouble(costField.getText())
            );
            controller.addMedication(medication);
            loadMedicationsData();
            dialog.dispose();
        });
        dialog.add(saveButton);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private void openEditMedicationDialog(String medicationId) {
        Medication medication = controller.getMedicationById(medicationId);
        if (medication == null) {
            JOptionPane.showMessageDialog(null, "Medication not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog();
        dialog.setTitle("Edit Medication");
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(5, 2));

        JTextField nameField = new JTextField(medication.getMedicationName());
        JTextField descriptionField = new JTextField(medication.getDescription());
        JTextField costField = new JTextField(String.valueOf(medication.getCost()));

        dialog.add(new JLabel("Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Description:"));
        dialog.add(descriptionField);
        dialog.add(new JLabel("Cost:"));
        dialog.add(costField);

        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(e -> {
            medication.setMedicationName(nameField.getText());
            medication.setDescription(descriptionField.getText());
            medication.setCost(Double.parseDouble(costField.getText()));
            controller.addMedication(medication);
            loadMedicationsData();
            dialog.dispose();
        });
        dialog.add(saveButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            controller.deleteMedicationById(medicationId);
            loadMedicationsData();
            dialog.dispose();
        });
        dialog.add(deleteButton);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Medications Tab Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new MedicationsTab().createMedicationsTab());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
