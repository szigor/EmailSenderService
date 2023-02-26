package me.igor.EmailSenderService.exception;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException(Long id) {
        super("Email with id: " + id + " does not exist.");
    }
}
