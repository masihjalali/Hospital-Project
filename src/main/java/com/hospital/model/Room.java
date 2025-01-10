// Path: src/main/java/com/hospital/model/Room.java
package com.hospital.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a Room in the hospital management system.
 */
public class Room {

    // Attributes
    private String roomId;
    private int roomNumber;
    private int capacity;
    private String roomType; // e.g., "General", "Private"
    private String status;   // e.g., "Occupied", "Available"
    private List<String> patientsInRoom; // List of Patient IDs

    // Constructor
    public Room(String roomId, int roomNumber, int capacity, String roomType, String status) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.roomType = roomType;
        this.status = status;
        this.patientsInRoom = new ArrayList<>();
    }

    // Getters and Setters
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getPatientsInRoom() {
        return patientsInRoom;
    }

    public void setPatientsInRoom(List<String> patientsInRoom) {
        this.patientsInRoom = patientsInRoom;
    }

    // Methods to manage patients in the room
    public void addPatient(String patientId) {
        if (this.patientsInRoom.size() < this.capacity) {
            this.patientsInRoom.add(patientId);
        } else {
            throw new IllegalStateException("Room capacity exceeded!");
        }
    }

    public void removePatient(String patientId) {
        this.patientsInRoom.remove(patientId);
    }

    // toString Method
    @Override
    public String toString() {
        return "Room{" +
                "roomId='" + roomId + '\'' +
                ", roomNumber=" + roomNumber +
                ", capacity=" + capacity +
                ", roomType='" + roomType + '\'' +
                ", status='" + status + '\'' +
                ", patientsInRoom=" + patientsInRoom +
                '}';
    }
}
