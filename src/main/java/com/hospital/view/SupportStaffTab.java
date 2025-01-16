package com.hospital.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.hospital.model.SupportStaff;
import com.hospital.controller.HospitalController;

/**
 * Support Staff Tab with Table and CRUD Operations
 */
public class SupportStaffTab {

    private JTable table;
    private DefaultTableModel tableModel;
    private HospitalController controller;

    public SupportStaffTab() {
        controller = new HospitalController();
    }

    public JPanel createSupportStaffTab() {
        JPanel panel = new JPanel(new BorderLayout());

        // Table model setup
        String[] columnNames = {"Staff ID", "Name", "Role", "Contact", "Assigned Section"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Load data into the table
        loadSupportStaffData();

        // Add JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();

        // Add Button
        JButton addButton = new JButton("Add Support Staff");
        addButton.addActionListener(e -> openAddSupportStaffDialog());
        buttonPanel.add(addButton);

        // Add to panel
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add Row Selection Listener
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    int row = table.getSelectedRow();
                    String staffId = tableModel.getValueAt(row, 0).toString();
                    openEditSupportStaffDialog(staffId);
                }
            }
        });

        return panel;
    }

    private void loadSupportStaffData() {
        tableModel.setRowCount(0); // Clear existing rows
        ArrayList<SupportStaff> staffList = controller.getAllSupportStaff();
        for (SupportStaff staff : staffList) {
            tableModel.addRow(new Object[]{
                    staff.getStaffId(),
                    staff.getName(),
                    staff.getRole(),
                    staff.getContactNumber(),
                    staff.getAssignedSection()
            });
        }
    }

    private void openAddSupportStaffDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Add Support Staff");
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(6, 2));

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField roleField = new JTextField();
        JTextField contactField = new JTextField();
        JTextField sectionField = new JTextField();

        dialog.add(new JLabel("Staff ID:"));
        dialog.add(idField);
        dialog.add(new JLabel("Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Role:"));
        dialog.add(roleField);
        dialog.add(new JLabel("Contact:"));
        dialog.add(contactField);
        dialog.add(new JLabel("Assigned Section:"));
        dialog.add(sectionField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            SupportStaff staff = new SupportStaff(
                    idField.getText(),
                    nameField.getText(),
                    roleField.getText(),
                    contactField.getText(),
                    sectionField.getText()
            );
            controller.addSupportStaff(staff);
            loadSupportStaffData();
            dialog.dispose();
        });
        dialog.add(saveButton);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private void openEditSupportStaffDialog(String staffId) {
        SupportStaff staff = controller.getSupportStaffById(staffId);
        if (staff == null) {
            JOptionPane.showMessageDialog(null, "Support Staff not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog();
        dialog.setTitle("Edit Support Staff");
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(6, 2));

        JTextField nameField = new JTextField(staff.getName());
        JTextField roleField = new JTextField(staff.getRole());
        JTextField contactField = new JTextField(staff.getContactNumber());
        JTextField sectionField = new JTextField(staff.getAssignedSection());

        dialog.add(new JLabel("Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Role:"));
        dialog.add(roleField);
        dialog.add(new JLabel("Contact:"));
        dialog.add(contactField);
        dialog.add(new JLabel("Assigned Section:"));
        dialog.add(sectionField);

        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(e -> {
            staff.setName(nameField.getText());
            staff.setRole(roleField.getText());
            staff.setContactNumber(contactField.getText());
            staff.setAssignedSection(sectionField.getText());
            controller.addSupportStaff(staff);
            loadSupportStaffData();
            dialog.dispose();
        });
        dialog.add(saveButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            controller.deleteSupportStaffById(staffId);
            loadSupportStaffData();
            dialog.dispose();
        });
        dialog.add(deleteButton);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Support Staff Tab Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new SupportStaffTab().createSupportStaffTab());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}