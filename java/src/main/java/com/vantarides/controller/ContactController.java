package com.vantarides.controller;

import com.vantarides.model.EmailRequest;
import com.vantarides.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin
public class ContactController {

    private final EmailService emailService;

    @Autowired
    public ContactController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> handleContact(@RequestBody EmailRequest request) {
        emailService.processAndSendEmail(request);
        return ResponseEntity.ok(Map.of("message", "Message sent and saved."));
    }
}
