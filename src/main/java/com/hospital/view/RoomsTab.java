package com.hospital.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.hospital.model.Room;
import com.hospital.controller.HospitalController;

/**
 * Rooms Tab with Table and CRUD Operations
 */
public class RoomsTab {

    private JTable table;
    private DefaultTableModel tableModel;
    private HospitalController controller;

    public RoomsTab() {
        controller = new HospitalController();
    }

    public JPanel createRoomsTab() {
        JPanel panel = new JPanel(new BorderLayout());

        // Table model setup
        String[] columnNames = {"id", "room_number", "capacity", "room_type", "status", "patients_in_room"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Load data into the table
        loadRoomsData();

        // Add JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();

        // Add addButton
        JButton addButton = new JButton("Add Room");
        addButton.addActionListener(e -> openAddRoomDialog());
        buttonPanel.add(addButton);

        // add edit button
        JButton editButton = new JButton("Edit Room");
        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String roomId = tableModel.getValueAt(selectedRow, 0).toString(); // Assuming Room ID is in the first column
                openEditRoomDialog(roomId);
            } else {
                JOptionPane.showMessageDialog(panel, "Please select a room to edit.", "No Selection", JOptionPane.WARNING_MESSAGE);
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
                    String roomId = tableModel.getValueAt(row, 0).toString();
                    openEditRoomDialog(roomId);
                }
            }
        });

        return panel;
    }

    private void loadRoomsData() {
        tableModel.setRowCount(0); // Clear existing rows
        ArrayList<Room> rooms = controller.getAllRooms();
        for (Room room : rooms) {
            tableModel.addRow(new Object[]{
                    room.getRoomId(),
                    room.getRoomNumber(),
                    room.getCapacity(),
                    room.getRoomType(),
                    room.getStatus(),
                    String.join(", ", room.getPatientsInRoom())
            });
        }
    }

    private void openAddRoomDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Add Room");
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(7, 2));

        JTextField idField = new JTextField();
        JTextField numberField = new JTextField();
        JTextField capacityField = new JTextField();
        JTextField typeField = new JTextField();
        JTextField statusField = new JTextField();
        JTextField patientsField = new JTextField();

        dialog.add(new JLabel("Room ID:"));
        dialog.add(idField);
        dialog.add(new JLabel("Room Number:"));
        dialog.add(numberField);
        dialog.add(new JLabel("Capacity:"));
        dialog.add(capacityField);
        dialog.add(new JLabel("Room Type (General/Private):"));
        dialog.add(typeField);
        dialog.add(new JLabel("Status (Occupied/Vacant):"));
        dialog.add(statusField);
        dialog.add(new JLabel("Patients In Room (comma-separated):"));
        dialog.add(patientsField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            Room room = new Room(
                    idField.getText(),
                    numberField.getText(),
                    Integer.parseInt(capacityField.getText()),
                    typeField.getText(),
                    statusField.getText()
            );
            String[] patients = patientsField.getText().split(",");
            for (String patient : patients) {
                room.addPatient(patient.trim());
            }
            controller.addRoom(room);
            loadRoomsData();
            dialog.dispose();
        });
        dialog.add(saveButton);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private void openEditRoomDialog(String roomId) {
        Room room = controller.getRoomById(roomId);
        if (room == null) {
            JOptionPane.showMessageDialog(null, "Room not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog();
        dialog.setTitle("Edit Room");
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(7, 2));

        JTextField numberField = new JTextField(room.getRoomNumber());
        JTextField capacityField = new JTextField(String.valueOf(room.getCapacity()));
        JTextField typeField = new JTextField(room.getRoomType());
        JTextField statusField = new JTextField(room.getStatus());
        JTextField patientsField = new JTextField(String.join(", ", room.getPatientsInRoom()));

        dialog.add(new JLabel("Room Number:"));
        dialog.add(numberField);
        dialog.add(new JLabel("Capacity:"));
        dialog.add(capacityField);
        dialog.add(new JLabel("Room Type (General/Private):"));
        dialog.add(typeField);
        dialog.add(new JLabel("Status (Occupied/Vacant):"));
        dialog.add(statusField);
        dialog.add(new JLabel("Patients In Room (comma-separated):"));
        dialog.add(patientsField);

        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(e -> {
            room.setRoomNumber(numberField.getText());
            room.setCapacity(Integer.parseInt(capacityField.getText()));
            room.setRoomType(typeField.getText());
            room.setStatus(statusField.getText());
            room.getPatientsInRoom().clear();
            String[] patients = patientsField.getText().split(",");
            for (String patient : patients) {
                room.addPatient(patient.trim());
            }

            loadRoomsData();
            dialog.dispose();
        });
        dialog.add(saveButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            controller.deleteRoomById(roomId);
            loadRoomsData();
            dialog.dispose();
        });
        dialog.add(deleteButton);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Rooms Tab Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new RoomsTab().createRoomsTab());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
