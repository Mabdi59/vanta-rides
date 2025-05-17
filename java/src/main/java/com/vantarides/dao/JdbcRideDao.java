package com.vantarides.dao;

import com.vantarides.exception.DaoException;
import com.vantarides.model.EnrichedRide;
import com.vantarides.model.Ride;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRideDao implements RideDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcRideDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Ride> getTodayRides() {
        List<Ride> todayRides = new ArrayList<>();
        String sql = """
            SELECT r.*
            FROM rides r
            JOIN ride_requests rq ON r.request_id = rq.request_id
            WHERE DATE(rq.pickup_time) = CURRENT_DATE
            ORDER BY rq.pickup_time ASC;
        """;

        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
            while (rs.next()) {
                todayRides.add(mapRowToRide(rs));
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error retrieving today's rides", e);
        }

        return todayRides;
    }

    @Override
    public void updateStatus(Ride ride) {
        String sql = """
            UPDATE rides
            SET status = ?, updated_at = CURRENT_TIMESTAMP
            WHERE ride_id = ?
        """;

        try {
            jdbcTemplate.update(sql, ride.getStatus(), ride.getRideId());
        } catch (DataAccessException e) {
            throw new DaoException("Error updating ride status", e);
        }
    }

    @Override
    public Ride create(Ride ride) {
        String sql = """
            INSERT INTO rides (request_id, vehicle_id, driver_id, status, assigned_at)
            VALUES (?, ?, ?, ?, ?)
            RETURNING ride_id;
        """;

        try {
            int newId = jdbcTemplate.queryForObject(
                    sql, int.class,
                    ride.getRequestId(),
                    ride.getVehicleId(),
                    ride.getDriverId(), // nullable
                    ride.getStatus(),
                    ride.getAssignedAt()
            );
            return getById(newId);
        } catch (DataAccessException e) {
            throw new DaoException("Error creating ride", e);
        }
    }

    @Override
    public List<Ride> getAll() {
        List<Ride> rides = new ArrayList<>();
        String sql = "SELECT * FROM rides ORDER BY assigned_at DESC";

        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
            while (rs.next()) {
                rides.add(mapRowToRide(rs));
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error retrieving rides", e);
        }

        return rides;
    }

    @Override
    public Ride getById(int rideId) {
        String sql = "SELECT * FROM rides WHERE ride_id = ?";

        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, rideId);
            if (rs.next()) {
                return mapRowToRide(rs);
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error getting ride by ID", e);
        }

        return null;
    }

    @Override
    public List<EnrichedRide> getAllEnrichedRides() {
        List<EnrichedRide> list = new ArrayList<>();

        String sql = """
            SELECT 
                r.ride_id, r.request_id, r.vehicle_id, r.driver_id, r.status,
                r.assigned_at, r.updated_at,
                rq.pickup_location, rq.dropoff_location, rq.pickup_time, rq.notes,
                v.make AS vehicle_make, v.model AS vehicle_model, v.color AS vehicle_color
            FROM rides r
            JOIN ride_requests rq ON r.request_id = rq.request_id
            LEFT JOIN vehicles v ON r.vehicle_id = v.vehicle_id
            ORDER BY r.assigned_at DESC;
        """;

        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
            while (rs.next()) {
                EnrichedRide e = new EnrichedRide();
                e.setRideId(rs.getInt("ride_id"));
                e.setRequestId(rs.getInt("request_id"));
                e.setVehicleId(rs.getInt("vehicle_id"));
                e.setStatus(rs.getString("status"));
                e.setAssignedAt(rs.getTimestamp("assigned_at").toLocalDateTime());

                if (rs.getTimestamp("updated_at") != null) {
                    e.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                }

                int driverId = rs.getInt("driver_id");
                if (!rs.wasNull()) {
                    e.setDriverId(driverId);
                }

                e.setPickupLocation(rs.getString("pickup_location"));
                e.setDropoffLocation(rs.getString("dropoff_location"));
                e.setPickupTime(rs.getTimestamp("pickup_time").toLocalDateTime());
                e.setNotes(rs.getString("notes"));

                e.setVehicleMake(rs.getString("vehicle_make"));
                e.setVehicleModel(rs.getString("vehicle_model"));
                e.setVehicleColor(rs.getString("vehicle_color"));

                list.add(e);
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error retrieving enriched ride data", e);
        }

        return list;
    }

    @Override
    public void updateDriver(Ride ride) {
        String sql = """
            UPDATE rides
            SET driver_id = ?, updated_at = CURRENT_TIMESTAMP
            WHERE ride_id = ?;
        """;

        try {
            jdbcTemplate.update(sql, ride.getDriverId(), ride.getRideId());
        } catch (DataAccessException e) {
            throw new DaoException("Error assigning driver to ride", e);
        }
    }

    private Ride mapRowToRide(SqlRowSet rs) {
        Ride ride = new Ride();
        ride.setRideId(rs.getInt("ride_id"));
        ride.setRequestId(rs.getInt("request_id"));
        ride.setVehicleId(rs.getInt("vehicle_id"));
        ride.setStatus(rs.getString("status"));
        ride.setAssignedAt(rs.getTimestamp("assigned_at").toLocalDateTime());

        int driverId = rs.getInt("driver_id");
        if (!rs.wasNull()) {
            ride.setDriverId(driverId);
        }

        if (rs.getTimestamp("updated_at") != null) {
            ride.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        }

        return ride;
    }
}
