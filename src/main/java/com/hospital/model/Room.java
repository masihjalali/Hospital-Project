package com.hospital.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a Room in the hospital management system.
 */
public class Room {

    // Attributes
    private String roomId; // Room ID
    private String roomNumber; // Room number
    private int capacity; // Capacity of the room
    private String roomType; // Room type (General/Private)
    private String status; // Status (Occupied/Vacant)
    private List<String> patientsInRoom; // List of patient IDs in the room

    // Constructor
    public Room(String roomId, String roomNumber, int capacity, String roomType, String status) {
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

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
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

    public void addPatient(String patientId) {
        if (this.patientsInRoom.size() < this.capacity) {
            this.patientsInRoom.add(patientId);
        } else {
            throw new IllegalStateException("Room is at full capacity");
        }
    }

    public void removePatient(String patientId) {
        this.patientsInRoom.remove(patientId);
    }

    // toString Method
    @Override
    public String toString() {
        return "Room{" +
                "roomId='" + roomId + '\\'' +
                ", roomNumber='" + roomNumber + '\\'' +
                ", capacity=" + capacity +
                ", roomType='" + roomType + '\\'' +
                ", status='" + status + '\\'' +
                ", patientsInRoom=" + patientsInRoom +
                '}';
    }
}
