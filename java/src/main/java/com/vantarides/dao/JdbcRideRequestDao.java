package com.vantarides.dao;

import com.vantarides.exception.DaoException;
import com.vantarides.model.RideRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRideRequestDao implements RideRequestDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcRideRequestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<RideRequest> getAll() {
        List<RideRequest> requests = new ArrayList<>();
        String sql = "SELECT * FROM ride_requests ORDER BY pickup_time DESC";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                requests.add(mapRowToRequest(results));
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error retrieving ride requests", e);
        }
        return requests;
    }

    @Override
    public RideRequest getById(int id) {
        String sql = "SELECT * FROM ride_requests WHERE request_id = ?";
        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, id);
            if (rs.next()) {
                return mapRowToRequest(rs);
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error retrieving ride request by ID", e);
        }
        return null;
    }

    @Override
    public RideRequest create(RideRequest request) {
        String sql = """
            INSERT INTO ride_requests (
                pickup_location, dropoff_location, pickup_time, notes, vehicle_id, user_id
            )
            VALUES (?, ?, ?, ?, ?, ?)
            RETURNING request_id;
            """;

        try {
            int newId = jdbcTemplate.queryForObject(sql, int.class,
                    request.getPickupLocation(),
                    request.getDropoffLocation(),
                    request.getPickupTime(),
                    request.getNotes(),
                    request.getVehicleId(),
                    request.getUserId()
            );

            return getById(newId);
        } catch (DataAccessException e) {
            throw new DaoException("Error creating ride request", e);
        }
    }

    @Override
    public boolean updateStatus(int id, String newStatus) {
        String sql = """
            UPDATE ride_requests
            SET status = ?, updated_at = CURRENT_TIMESTAMP
            WHERE request_id = ?
            """;

        try {
            return jdbcTemplate.update(sql, newStatus, id) == 1;
        } catch (DataAccessException e) {
            throw new DaoException("Error updating ride request status", e);
        }
    }

    private RideRequest mapRowToRequest(SqlRowSet rs) {
        RideRequest r = new RideRequest();
        r.setRequestId(rs.getInt("request_id"));
        r.setPickupLocation(rs.getString("pickup_location"));
        r.setDropoffLocation(rs.getString("dropoff_location"));
        r.setPickupTime(rs.getTimestamp("pickup_time").toLocalDateTime());
        r.setNotes(rs.getString("notes"));
        r.setStatus(rs.getString("status"));
        r.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

        // Optional fields
        if (rs.getTimestamp("updated_at") != null) {
            r.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        }
        int vehicleId = rs.getInt("vehicle_id");
        if (!rs.wasNull()) {
            r.setVehicleId(vehicleId);
        }
        int userId = rs.getInt("user_id");
        if (!rs.wasNull()) {
            r.setUserId(userId);
        }

        return r;
    }
}
