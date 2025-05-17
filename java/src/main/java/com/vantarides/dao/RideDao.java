package com.vantarides.dao;

import com.vantarides.model.Ride;
import com.vantarides.model.EnrichedRide;

import java.util.List;

public interface RideDao {
    Ride create(Ride ride);
    List<Ride> getAll();
    Ride getById(int rideId);
    List<EnrichedRide> getAllEnrichedRides();
    List<Ride> getTodayRides();
    void updateDriver(Ride ride);
    void updateStatus(Ride ride);
}
