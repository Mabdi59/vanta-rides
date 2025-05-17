// VehicleController.java
package com.vantarides.controller;

import com.vantarides.dao.VehicleDao;
import com.vantarides.model.Vehicle;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles") // ✅ plural
@CrossOrigin
public class VehicleController {

    private final VehicleDao vehicleDao;

    public VehicleController(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleDao.getAllVehicles();
    }

    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable int id) {
        Vehicle vehicle = vehicleDao.getVehicleById(id);
        if (vehicle == null) {
            throw new RuntimeException("Vehicle not found with ID: " + id);
        }
        return vehicle;
    }
}
