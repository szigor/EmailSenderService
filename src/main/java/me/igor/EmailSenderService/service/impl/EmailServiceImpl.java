package me.igor.EmailSenderService.service.impl;

import me.igor.EmailSenderService.exception.EmailCantBeSavedException;
import me.igor.EmailSenderService.exception.EmailNotFoundException;
import me.igor.EmailSenderService.model.Email;
import me.igor.EmailSenderService.repository.EmailRepository;
import me.igor.EmailSenderService.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static me.igor.EmailSenderService.logs.LoggerUtils.logError;
import static me.igor.EmailSenderService.logs.LoggerUtils.logInfo;


@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private EmailRepository emailRepository;

    @Override
    public List<Email> getAllEmails() {
        logInfo(LOGGER, "Retrieved all emails from the database.");
        return emailRepository.findAll(Sort.by("id"));
    }

    @Override
    public Email getEmailById(Long id) {
        logInfo(LOGGER, String.format("Retrieved email with ID %s from the database.", id));
        return emailRepository.findById(id).orElseThrow(() -> new EmailNotFoundException(id));
    }

    @Override
    public void addEmail(Email email) {
        safeSaveEmail(email);
        logInfo(LOGGER, String.format("Added email with ID %s to the database.", email.getId()));
    }

    @Override
    public void updateEmail(Long id, Email email) {
        if (isEmailByIdExists(id)) {
            Email existingEmail = emailRepository.findById(id).get();
            existingEmail.setEmail(email.getEmail());
            safeSaveEmail(existingEmail);
            logInfo(LOGGER, String.format("Updated email with ID %s in the database.", id));
        }
    }

    @Override
    public void deleteEmail(Long id) {
        if (isEmailByIdExists(id)) {
            emailRepository.deleteById(id);
            logInfo(LOGGER, String.format("Deleted email with ID %s from the database.", id));
        }
    }

    private void safeSaveEmail(Email email) {
        try {
            emailRepository.save(email);
        } catch (DataAccessException e) {
            throw new EmailCantBeSavedException(e);
        }
    }

    private boolean isEmailByIdExists(Long id) {
        if (emailRepository.existsById(id)) {
            return true;
        } else {
            logError(LOGGER, String.format("Email with ID %s does not exist in the database.", id));
            throw new EmailNotFoundException(id);
        }
    }

}
