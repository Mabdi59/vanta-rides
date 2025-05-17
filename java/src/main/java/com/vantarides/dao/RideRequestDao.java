package com.vantarides.dao;

import com.vantarides.model.RideRequest;

import java.util.List;

public interface RideRequestDao {
    List<RideRequest> getAll();
    RideRequest getById(int id);
    RideRequest create(RideRequest request);
    boolean updateStatus(int id, String newStatus);
}
