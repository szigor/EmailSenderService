package me.igor.EmailSenderService.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import me.igor.EmailSenderService.dto.EmailRequestDTO;
import me.igor.EmailSenderService.model.EmailMessage;
import me.igor.EmailSenderService.repository.EmailRepository;
import me.igor.EmailSenderService.service.EmailSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static me.igor.EmailSenderService.logs.LoggerUtils.logException;
import static me.igor.EmailSenderService.logs.LoggerUtils.logInfo;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailSenderServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailRepository emailRepository;

    @Override
    public void sendEmailToAll(EmailRequestDTO emailRequestDTO) {

        List<String> emails = new ArrayList<>();

        emailRepository.findAll().forEach(email -> emails.add(email.getEmail()));

        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setFrom(emailRequestDTO.getFrom());
        emailMessage.setTo(emails);
        emailMessage.setSubject(emailRequestDTO.getSubject());
        emailMessage.setText(emailRequestDTO.getContent());

        sendEmail(emailMessage);

    }

    private void sendEmail(EmailMessage emailMessage) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(emailMessage.getFrom());
            mimeMessageHelper.setTo(emailMessage.getTo().toArray(new String[emailMessage.getTo().size()]));
            mimeMessageHelper.setSubject(emailMessage.getSubject());
            mimeMessageHelper.setText(emailMessage.getText(), true);

        } catch (MessagingException e) {
            logException(LOGGER, e);
        }

        javaMailSender.send(mimeMessage);
        logInfo(LOGGER, "Email successfully sent.");
    }

}
