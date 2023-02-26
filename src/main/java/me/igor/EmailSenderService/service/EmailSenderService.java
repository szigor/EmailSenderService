package me.igor.EmailSenderService.service;

import jakarta.mail.MessagingException;
import me.igor.EmailSenderService.dto.EmailRequestDTO;

public interface EmailSenderService {

    void sendEmailToAll(EmailRequestDTO emailRequestDTO) throws MessagingException;

}
