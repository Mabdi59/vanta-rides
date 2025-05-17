package com.vantarides.model;

import java.time.LocalDateTime;

public class Ride {

    private int rideId;
    private int requestId;
    private int vehicleId;
    private Integer driverId; // 🆕 optional
    private String status;
    private LocalDateTime assignedAt;
    private LocalDateTime updatedAt; // 🆕

    public Ride() {
    }

    public Ride(int rideId, int requestId, int vehicleId, Integer driverId,
                String status, LocalDateTime assignedAt, LocalDateTime updatedAt) {
        this.rideId = rideId;
        this.requestId = requestId;
        this.vehicleId = vehicleId;
        this.driverId = driverId;
        this.status = status;
        this.assignedAt = assignedAt;
        this.updatedAt = updatedAt;
    }

    public int getRideId() {
        return rideId;
    }

    public void setRideId(int rideId) {
        this.rideId = rideId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(LocalDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "rideId=" + rideId +
                ", requestId=" + requestId +
                ", vehicleId=" + vehicleId +
                ", driverId=" + driverId +
                ", status='" + status + '\'' +
                ", assignedAt=" + assignedAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
