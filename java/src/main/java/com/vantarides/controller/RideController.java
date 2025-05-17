package com.vantarides.controller;

import com.vantarides.dao.RideDao;
import com.vantarides.model.Ride;
import com.vantarides.model.EnrichedRide;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rides")
@CrossOrigin
public class RideController {

    private final RideDao rideDao;

    public RideController(RideDao rideDao) {
        this.rideDao = rideDao;
    }

    @GetMapping
    public List<Ride> getAllRides() {
        return rideDao.getAll();
    }

    @GetMapping("/today")
    public List<Ride> getTodayRides() {
        return rideDao.getTodayRides();
    }


    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateRideStatus(@PathVariable int id, @RequestBody Map<String, String> body) {
        String newStatus = body.get("status");
        if (newStatus == null || newStatus.isBlank()) {
            return ResponseEntity.badRequest().body("Status is required.");
        }

        Ride ride = rideDao.getById(id);
        if (ride == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ride not found.");
        }

        ride.setStatus(newStatus.toUpperCase());
        ride.setUpdatedAt(LocalDateTime.now());
        rideDao.updateStatus(ride);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/enriched")
    public List<EnrichedRide> getAllEnrichedRides() {
        return rideDao.getAllEnrichedRides();
    }

    @GetMapping("/{id}")
    public Ride getRideById(@PathVariable int id) {
        Ride ride = rideDao.getById(id);
        if (ride == null) {
            throw new RuntimeException("Ride not found with ID: " + id);
        }
        return ride;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ride createRide(@RequestBody Ride ride) {
        if (ride.getAssignedAt() == null) {
            ride.setAssignedAt(LocalDateTime.now());
        }
        if (ride.getStatus() == null || ride.getStatus().isBlank()) {
            ride.setStatus("CONFIRMED");
        }
        return rideDao.create(ride);
    }

    @PutMapping("/{id}/driver")
    public ResponseEntity<?> assignDriver(@PathVariable int id, @RequestBody Map<String, Integer> body) {
        Integer driverId = body.get("driverId");
        if (driverId == null) {
            return ResponseEntity.badRequest().body("Driver ID is required.");
        }

        Ride ride = rideDao.getById(id);
        if (ride == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ride not found.");
        }

        ride.setDriverId(driverId);
        rideDao.updateDriver(ride);
        return ResponseEntity.ok().build();
    }
}
