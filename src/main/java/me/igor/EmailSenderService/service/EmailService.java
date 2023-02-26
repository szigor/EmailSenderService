package me.igor.EmailSenderService.service;

import me.igor.EmailSenderService.model.Email;

import java.util.List;

public interface EmailService {

    List<Email> getAllEmails();

    Email getEmailById(Long id);

    void addEmail(Email email);

    void updateEmail(Long id, Email email);

    void deleteEmail(Long id);

}
