package com.vantarides.dao;

import com.vantarides.exception.DaoException;
import com.vantarides.model.Vehicle;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcVehicleDao implements VehicleDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcVehicleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                vehicles.add(mapRowToVehicle(results));
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error retrieving vehicles", e);
        }
        return vehicles;
    }

    @Override
    public Vehicle getVehicleById(int id) {
        String sql = "SELECT * FROM vehicles WHERE vehicle_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                return mapRowToVehicle(results);
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error retrieving vehicle by ID", e);
        }
        return null;
    }

    private Vehicle mapRowToVehicle(SqlRowSet rs) {
        Vehicle v = new Vehicle();
        v.setVehicleId(rs.getInt("vehicle_id"));
        v.setMake(rs.getString("make"));
        v.setModel(rs.getString("model"));
        v.setYear(rs.getInt("year"));
        v.setLicensePlate(rs.getString("license_plate"));
        v.setColor(rs.getString("color"));
        v.setCapacity(rs.getInt("capacity"));
        return v;
    }
}
