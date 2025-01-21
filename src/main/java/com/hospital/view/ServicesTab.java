package com.hospital.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.hospital.model.Service;
import com.hospital.controller.HospitalController;

/**
 * Services Tab with Table and CRUD Operations
 */
public class ServicesTab {

    private JTable table;
    private DefaultTableModel tableModel;
    private HospitalController controller;

    public ServicesTab() {
        controller = new HospitalController();
    }

    public JPanel createServicesTab() {
        JPanel panel = new JPanel(new BorderLayout());

        // Table model setup
        String[] columnNames = {"Service ID", "Service Name", "Cost", "Description", "Responsible Staff"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Load data into the table
        loadServicesData();

        // Add JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();

        // Add Button
        JButton addButton = new JButton("Add Service");
        addButton.addActionListener(e -> openAddServiceDialog());
        buttonPanel.add(addButton);

        // Add Edit Service Button
        JButton editButton = new JButton("Edit Service");
        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String serviceId = tableModel.getValueAt(selectedRow, 0).toString(); // Assuming Service ID is in the first column
                openEditServiceDialog(serviceId);
            } else {
                JOptionPane.showMessageDialog(panel, "Please select a service to edit.", "No Selection", JOptionPane.WARNING_MESSAGE);
            }
        });
        buttonPanel.add(editButton);

        // Add to panel
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add Row Selection Listener
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    int row = table.getSelectedRow();
                    String serviceId = tableModel.getValueAt(row, 0).toString();
                    openEditServiceDialog(serviceId);
                }
            }
        });

        return panel;
    }

    private void loadServicesData() {
        tableModel.setRowCount(0); // Clear existing rows
        ArrayList<Service> services = controller.getAllServices();
        for (Service service : services) {
            tableModel.addRow(new Object[]{
                    service.getServiceId(),
                    service.getServiceName(),
                    service.getCost(),
                    service.getDescription(),
                    String.join(", ", service.getResponsibleStaff())
            });
        }
    }

    private void openAddServiceDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Add Service");
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(6, 2));

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField costField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField staffField = new JTextField();

        dialog.add(new JLabel("Service ID:"));
        dialog.add(idField);
        dialog.add(new JLabel("Service Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Cost:"));
        dialog.add(costField);
        dialog.add(new JLabel("Description:"));
        dialog.add(descriptionField);
        dialog.add(new JLabel("Responsible Staff (comma-separated):"));
        dialog.add(staffField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            Service service = new Service(
                    idField.getText(),
                    nameField.getText(),
                    Double.parseDouble(costField.getText()),
                    descriptionField.getText()
            );
            String[] staff = staffField.getText().split(",");
            for (String s : staff) {
                service.addResponsibleStaff(s.trim());
            }
            controller.addService(service);
            loadServicesData();
            dialog.dispose();
        });
        dialog.add(saveButton);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private void openEditServiceDialog(String serviceId) {
        Service service = controller.getServiceById(serviceId);
        if (service == null) {
            JOptionPane.showMessageDialog(null, "Service not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog();
        dialog.setTitle("Edit Service");
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(6, 2));

        JTextField nameField = new JTextField(service.getServiceName());
        JTextField costField = new JTextField(String.valueOf(service.getCost()));
        JTextField descriptionField = new JTextField(service.getDescription());
        JTextField staffField = new JTextField(String.join(", ", service.getResponsibleStaff()));

        dialog.add(new JLabel("Service Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Cost:"));
        dialog.add(costField);
        dialog.add(new JLabel("Description:"));
        dialog.add(descriptionField);
        dialog.add(new JLabel("Responsible Staff (comma-separated):"));
        dialog.add(staffField);

        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(e -> {
            service.setServiceName(nameField.getText());
            service.setCost(Double.parseDouble(costField.getText()));
            service.setDescription(descriptionField.getText());
            service.getResponsibleStaff().clear();
            String[] staff = staffField.getText().split(",");
            for (String s : staff) {
                service.addResponsibleStaff(s.trim());
            }
            controller.addService(service);
            loadServicesData();
            dialog.dispose();
        });
        dialog.add(saveButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            controller.deleteServiceById(serviceId);
            loadServicesData();
            dialog.dispose();
        });
        dialog.add(deleteButton);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Services Tab Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new ServicesTab().createServicesTab());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}