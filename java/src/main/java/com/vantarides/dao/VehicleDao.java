package com.vantarides.dao;

import com.vantarides.model.Vehicle;

import java.util.List;

public interface VehicleDao {
    List<Vehicle> getAllVehicles();
    Vehicle getVehicleById(int id);
}
