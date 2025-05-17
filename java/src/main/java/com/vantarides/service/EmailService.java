package com.vantarides.service;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Content;
import com.vantarides.dao.EmailDao;
import com.vantarides.model.EmailRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    private final EmailDao emailDao;
    private final String sendGridApiKey;
    private final String senderEmail;
    private final String senderName;

    public EmailService(
            EmailDao emailDao,
            @Value("${sendgrid.api.key}") String sendGridApiKey,
            @Value("${sendgrid.from.email}") String senderEmail,
            @Value("${sendgrid.from.name}") String senderName
    ) {
        this.emailDao = emailDao;
        this.sendGridApiKey = sendGridApiKey;
        this.senderEmail = senderEmail;
        this.senderName = senderName;
    }

    public void processAndSendEmail(EmailRequest request) {
        // Save message to DB
        emailDao.saveMessage(request);

        // Prepare email
        Email from = new Email(senderEmail, senderName);
        Email to = new Email(senderEmail); // Receiving to same verified email
        String subject = "New Contact Message from " + request.getName();
        String body = String.format("From: %s\nEmail: %s\n\nMessage:\n%s",
                request.getName(),
                request.getEmail(),
                request.getMessage());

        Content content = new Content("text/plain", body);
        Mail mail = new Mail(from, subject, to, content);

        // Send using SendGrid
        SendGrid sg = new SendGrid(sendGridApiKey);
        Request sendRequest = new Request();

        try {
            sendRequest.setMethod(Method.POST);
            sendRequest.setEndpoint("mail/send");
            sendRequest.setBody(mail.build());

            Response response = sg.api(sendRequest);
            System.out.println("✅ SendGrid status: " + response.getStatusCode());
            System.out.println("📭 Response headers: " + response.getHeaders());

            if (response.getStatusCode() != 202) {
                throw new RuntimeException("Failed to send email. SendGrid returned status: " + response.getStatusCode());
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send email via SendGrid", e);
        }
    }
}
