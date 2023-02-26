package me.igor.EmailSenderService.controller;

import jakarta.mail.MessagingException;
import me.igor.EmailSenderService.dto.EmailRequestDTO;
import me.igor.EmailSenderService.logs.LoggerUtils;
import me.igor.EmailSenderService.service.EmailSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static me.igor.EmailSenderService.logs.LoggerUtils.logRequest;

@RestController
@RequestMapping("/api/email")
public class EmailSenderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailSenderController.class);

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmailToAll(@RequestBody EmailRequestDTO emailRequestDTO) {
        try {
            emailSenderService.sendEmailToAll(emailRequestDTO);
            logRequest(LOGGER, "POST", "/api/email/send", emailRequestDTO.toString());
            return new ResponseEntity<>("E-mail was successfully sent to all", HttpStatus.OK);
        } catch (MessagingException e) {
            return new ResponseEntity<>("Failed to send e-mail: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
