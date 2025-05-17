package com.vantarides.dao;

import com.vantarides.model.EmailRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcEmailDao implements EmailDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcEmailDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveMessage(EmailRequest request) {
        String sql = "INSERT INTO contact_messages (name, email, message) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, request.getName(), request.getEmail(), request.getMessage());
    }
}
