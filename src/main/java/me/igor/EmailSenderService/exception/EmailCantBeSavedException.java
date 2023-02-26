package me.igor.EmailSenderService.exception;

public class EmailCantBeSavedException extends RuntimeException {

    public EmailCantBeSavedException(String message) {
        super(message);
    }
    public EmailCantBeSavedException(Throwable cause) {
        super("Email cannot be saved", cause);
    }
}
