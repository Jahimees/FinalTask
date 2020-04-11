package by.epam.ft.service.mail;

import by.epam.ft.service.PropertyService;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Properties;

public class MailAuthenticator extends Authenticator {

    private String email;
    private String password;

    public MailAuthenticator() {
        Properties properties = PropertyService.getProperties();
        email = properties.getProperty("SMTP_AUTH_USER");
        password = properties.getProperty("SMTP_AUTH_PWD");
    }

    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(email, password);
    }
}
