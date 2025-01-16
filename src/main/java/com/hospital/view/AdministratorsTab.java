package com.hospital.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.hospital.model.Administrator;
import com.hospital.controller.HospitalController;

/**
 * Administrators Tab with Table and CRUD Operations
 */
public class AdministratorsTab {

    private JTable table;
    private DefaultTableModel tableModel;
    private HospitalController controller;

    public AdministratorsTab() {
        controller = new HospitalController();
    }

    public JPanel createAdministratorsTab() {
        JPanel panel = new JPanel(new BorderLayout());

        // Table model setup
        String[] columnNames = {"Admin ID", "Name", "Username", "Contact"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Load data into the table
        loadAdministratorsData();

        // Add JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();

        // Add Button
        JButton addButton = new JButton("Add Administrator");
        addButton.addActionListener(e -> openAddAdministratorDialog());
        buttonPanel.add(addButton);

        // Add to panel
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add Row Selection Listener
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    int row = table.getSelectedRow();
                    String adminId = tableModel.getValueAt(row, 0).toString();
                    openEditAdministratorDialog(adminId);
                }
            }
        });

        return panel;
    }

    private void loadAdministratorsData() {
        tableModel.setRowCount(0); // Clear existing rows
        ArrayList<Administrator> admins = controller.getAllAdministrators();
        for (Administrator admin : admins) {
            tableModel.addRow(new Object[]{
                    admin.getId(),
                    admin.getName(),
                    admin.getUsername(),
                    admin.getContactNumber()
            });
        }
    }

    private void openAddAdministratorDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Add Administrator");
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(5, 2));

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField usernameField = new JTextField();
        JTextField passwordField = new JTextField();
        JTextField contactField = new JTextField();

        dialog.add(new JLabel("Admin ID:"));
        dialog.add(idField);
        dialog.add(new JLabel("Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Username:"));
        dialog.add(usernameField);
        dialog.add(new JLabel("Password:"));
        dialog.add(passwordField);
        dialog.add(new JLabel("Contact:"));
        dialog.add(contactField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            Administrator admin = new Administrator(
                    idField.getText(),
                    nameField.getText(),
                    usernameField.getText(),
                    passwordField.getText(),
                    contactField.getText()
            );
            controller.addAdministrator(admin);
            loadAdministratorsData();
            dialog.dispose();
        });
        dialog.add(saveButton);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private void openEditAdministratorDialog(String adminId) {
        Administrator admin = controller.getAdministratorById(adminId);
        if (admin == null) {
            JOptionPane.showMessageDialog(null, "Administrator not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog();
        dialog.setTitle("Edit Administrator");
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(5, 2));

        JTextField nameField = new JTextField(admin.getName());
        JTextField usernameField = new JTextField(admin.getUsername());
        JTextField passwordField = new JTextField();
        JTextField contactField = new JTextField(admin.getContactNumber());

        dialog.add(new JLabel("Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Username:"));
        dialog.add(usernameField);
        dialog.add(new JLabel("Password (leave blank to keep unchanged):"));
        dialog.add(passwordField);
        dialog.add(new JLabel("Contact:"));
        dialog.add(contactField);

        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(e -> {
            admin.setName(nameField.getText());
            admin.setUsername(usernameField.getText());
            if (!passwordField.getText().isEmpty()) {
                admin.setPassword(passwordField.getText());
            }
            admin.setContactNumber(contactField.getText());
            controller.addAdministrator(admin);
            loadAdministratorsData();
            dialog.dispose();
        });
        dialog.add(saveButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            controller.deleteAdministratorById(adminId);
            loadAdministratorsData();
            dialog.dispose();
        });
        dialog.add(deleteButton);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Administrators Tab Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new AdministratorsTab().createAdministratorsTab());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
