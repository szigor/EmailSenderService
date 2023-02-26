package me.igor.EmailSenderService.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class EmailMessage {

    private String from;
    private List<String> to;
    private String subject;
    private String text;

}
