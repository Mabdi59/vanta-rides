package com.vantarides.model;

import java.time.LocalDateTime;

public class RideRequest {

    private int requestId;
    private String pickupLocation;
    private String dropoffLocation;
    private LocalDateTime pickupTime;
    private String notes;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer vehicleId;
    private Integer userId;           

    public RideRequest() {
    }

    public RideRequest(int requestId, String pickupLocation, String dropoffLocation, LocalDateTime pickupTime,
                       String notes, String status, LocalDateTime createdAt, LocalDateTime updatedAt,
                       Integer vehicleId, Integer userId) {
        this.requestId = requestId;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.pickupTime = pickupTime;
        this.notes = notes;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.vehicleId = vehicleId;
        this.userId = userId;
    }

    // Getters and setters for all fields

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropoffLocation() {
        return dropoffLocation;
    }

    public void setDropoffLocation(String dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }

    public LocalDateTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(LocalDateTime pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "RideRequest{" +
                "requestId=" + requestId +
                ", pickupLocation='" + pickupLocation + '\'' +
                ", dropoffLocation='" + dropoffLocation + '\'' +
                ", pickupTime=" + pickupTime +
                ", notes='" + notes + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", vehicleId=" + vehicleId +
                ", userId=" + userId +
                '}';
    }
}
