package com.vantarides.controller;

import com.vantarides.dao.RideDao;
import com.vantarides.dao.RideRequestDao;
import com.vantarides.model.Ride;
import com.vantarides.model.RideRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin
public class RideRequestController {

    private final RideRequestDao rideRequestDao;
    private final RideDao rideDao;

    public RideRequestController(RideRequestDao rideRequestDao, RideDao rideDao) {
        this.rideRequestDao = rideRequestDao;
        this.rideDao = rideDao;
    }

    // GET /api/bookings
    @GetMapping
    public List<RideRequest> getAllBookings() {
        return rideRequestDao.getAll();
    }

    // POST /api/bookings
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RideRequest createBooking(@RequestBody RideRequest rideRequest) {
        // Optional validation: ensure pickupTime is in the future
        if (rideRequest.getPickupTime() == null || rideRequest.getPickupTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Pickup time must be in the future.");
        }

        return rideRequestDao.create(rideRequest);
    }

    // PUT /api/bookings/{id}/status
    @PutMapping("/{id}/status")
    public void updateStatus(@PathVariable int id, @RequestBody Map<String, String> body) {
        String newStatus = body.get("status");

        if (newStatus == null || newStatus.isBlank()) {
            throw new IllegalArgumentException("Status must be provided.");
        }

        boolean success = rideRequestDao.updateStatus(id, newStatus.toUpperCase());

        if (!success) {
            throw new RuntimeException("Failed to update booking status.");
        }

        // ✅ Auto-create a ride entry once status becomes CONFIRMED
        if (newStatus.equalsIgnoreCase("CONFIRMED")) {
            Ride ride = new Ride();
            ride.setRequestId(id);
            ride.setVehicleId(1); // You could pull this dynamically later from rideRequest
            ride.setStatus("CONFIRMED");
            ride.setAssignedAt(LocalDateTime.now());
            // Optionally set driverId if known

            rideDao.create(ride);
        }
    }
}
