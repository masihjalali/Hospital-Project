package com.hospital.view;

import javax.swing.*;
import java.awt.*;

/**
 * Main Panel for Hospital Management System.
 */
public class MainPanel {

    public static void main(String[] args) {
        // Create the JFrame
        JFrame frame = new JFrame("Hospital Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create the Tabbed Pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Add Tabs
        tabbedPane.addTab("Patients", new PatientsTab().createPatientsTab());
        tabbedPane.addTab("Doctors", new DoctorsTab().createDoctorsTab());
        tabbedPane.addTab("Rooms", new RoomsTab().createRoomsTab());
        tabbedPane.addTab("Services", new ServicesTab().createServicesTab());
        tabbedPane.addTab("Support Staff", new SupportStaffTab().createSupportStaffTab());
        tabbedPane.addTab("Administrators", new AdministratorsTab().createAdministratorsTab());
        tabbedPane.addTab("Nurses", new NursesTab().createNursesTab());
        tabbedPane.addTab("Medications", new MedicationsTab().createMedicationsTab());
        tabbedPane.addTab("Appointments", new AppointmentsTab().createAppointmentsTab());

        // Add the TabbedPane to the Frame
        frame.add(tabbedPane);

        // Set frame properties
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
