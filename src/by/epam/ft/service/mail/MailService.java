package by.epam.ft.service.mail;

import by.epam.ft.entity.EmailMessage;
import by.epam.ft.service.PropertyService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailService {

    private Properties mailProperties;

    public MailService() {
        Properties configProperties = PropertyService.getProperties();
        mailProperties = new Properties();
        mailProperties.put("mail.smtp.host", configProperties.getProperty("SMTP_HOST"));
        mailProperties.put("mail.smtp.port", configProperties.getProperty("SMTP_PORT"));
        mailProperties.put("mail.smtp.auth", "true");
        mailProperties.put("mail.smtp.ssl.enable", "true");
        mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

    }

    public Session authenticate() {
        Authenticator authenticator = new MailAuthenticator();
        Session session = Session.getDefaultInstance(mailProperties, authenticator);
        session.setDebug(false);

        return session;
    }

    public void sendEmail(EmailMessage emailMessage) throws MessagingException {
        Session session = authenticate();
        InternetAddress emailFrom = new InternetAddress(emailMessage.getSender());
        InternetAddress emailTo = new InternetAddress(emailMessage.getReceiver());

        Message message = new MimeMessage(session);
        message.setFrom(emailFrom);
        message.setRecipient(Message.RecipientType.TO, emailTo);
        message.setSubject(emailMessage.getTitle());
        ((MimeMessage) message).setText(emailMessage.getMessage(), "UTF-8");

        Transport.send(message);
    }
}
