package com.hospital.model;

/**
 * Class representing an Administrator in the hospital management system.
 */
public class Administrator {

    // Attributes
    private String id; // Administrator ID
    private String name; // Full name of the administrator
    private String username; // Username for login
    private String password; // Password for login
    private String contactNumber; // Contact number

    // Constructor
    public Administrator(String id, String name, String username, String password, String contactNumber) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.contactNumber = contactNumber;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

}
