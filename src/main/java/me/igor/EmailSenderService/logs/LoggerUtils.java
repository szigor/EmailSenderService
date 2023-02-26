package me.igor.EmailSenderService.logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerUtils {

    private static final Logger log = LoggerFactory.getLogger(LoggerUtils.class);

    private static final String INTEGRATION_LOGS_PATH = "src/main/java/me/igor/EmailSenderService/logs/integrationLogs.log";

    private static final String GENERAL_LOGS_PATH = "src/main/java/me/igor/EmailSenderService/logs/generalLogs.log";

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public static void logRequest(Logger logger, String method, String endpoint) {
        String message = String.format("[%s] [REQUEST] %s %s", LocalDateTime.now().format(FORMATTER), method, endpoint);
        logger.info(message);
        writeToFile(message, INTEGRATION_LOGS_PATH);
    }

    public static void logRequest(Logger logger, String method, String endpoint, String body) {
        String message = String.format("[%s] [REQUEST] %s %s - body: %s", LocalDateTime.now().format(FORMATTER), method, endpoint, body);
        logger.info(message);
        writeToFile(message, INTEGRATION_LOGS_PATH);
    }

    public static void logException(Logger logger, Exception e) {
        String message = String.format("[%s] [ERROR] %s", LocalDateTime.now().format(FORMATTER), e.getMessage());
        logger.error(message, e);
        writeToFile(message, GENERAL_LOGS_PATH);
    }

    public static void logInfo(Logger logger, String message) {
        String endMessage = String.format("[%s] [INFO] %s", LocalDateTime.now().format(FORMATTER), message);
        logger.info(message);
        writeToFile(endMessage, GENERAL_LOGS_PATH);
    }

    public static void logError(Logger logger, String message) {
        String endMessage = String.format("[%s] [ERROR] %s", LocalDateTime.now().format(FORMATTER), message);
        logger.error(message);
        writeToFile(endMessage, GENERAL_LOGS_PATH);
    }

    private static void writeToFile(String message, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            fileWriter.write(message);
            fileWriter.write(System.lineSeparator());
        } catch (IOException e) {
            log.error("[ERROR] Failed to write to file", e);
        }
    }

}
