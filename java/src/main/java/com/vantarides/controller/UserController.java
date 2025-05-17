package com.vantarides.controller;

import com.vantarides.dao.JdbcUserDao;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final JdbcUserDao userDao;

    public UserController(JdbcUserDao userDao) {
        this.userDao = userDao;
    }

    @PutMapping("/password")
    @ResponseStatus(HttpStatus.OK)
    public void updatePassword(@RequestBody Map<String, String> body) {
        String newPassword = body.get("newPassword");

        if (newPassword == null || newPassword.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New password is required.");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        boolean updated = userDao.updatePassword(username, newPassword);

        if (!updated) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Password update failed.");
        }
    }
}
