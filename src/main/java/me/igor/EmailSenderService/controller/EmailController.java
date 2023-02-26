package me.igor.EmailSenderService.controller;

import me.igor.EmailSenderService.model.Email;
import me.igor.EmailSenderService.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static me.igor.EmailSenderService.logs.LoggerUtils.logRequest;
import static me.igor.EmailSenderService.logs.LoggerUtils.logException;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private EmailService emailService;

    @GetMapping
    public List<Email> getAllEmails() {
        logRequest(LOGGER, "GET", "/api/email");
        return emailService.getAllEmails();
    }

    @GetMapping("/{id}")
    public Email getEmailById(@PathVariable Long id) {
        logRequest(LOGGER, "GET", "/api/email/" + id);
        return emailService.getEmailById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addEmail(@RequestBody Email email) {
        emailService.addEmail(email);
        logRequest(LOGGER, "POST", "/api/email", email.toString());
    }

    // don't need to add @responseStatus bcs default values are 200 and 404, so for PUT & DELETE it is fine

    @PutMapping("/{id}")
    public void updateEmail(@PathVariable Long id, @RequestBody Email email) {
        emailService.updateEmail(id, email);
        logRequest(LOGGER, "PUT", "/api/email/" + id, email.toString());
    }

    @DeleteMapping("/{id}")
    public void deleteEmail(@PathVariable Long id) {
        emailService.deleteEmail(id);
        logRequest(LOGGER, "DELETE", "/api/email/" + id);
    }

    @ExceptionHandler(Exception.class)
    public void handleException(Exception e) {
        logException(LOGGER, e);
    }

}
