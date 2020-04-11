package by.epam.ft.entity;

import by.epam.ft.service.PropertyService;

import java.util.Properties;

public class EmailMessage {

    private String title;
    private String message;
    private String receiver;
    private String sender;

    public EmailMessage() {
        Properties properties = PropertyService.getProperties();
        sender = properties.getProperty("SMTP_AUTH_USER");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
