package test.by.epam.ft.service;

import by.epam.ft.entity.EmailMessage;
import by.epam.ft.service.mail.MailService;
import org.testng.annotations.Test;

import javax.mail.MessagingException;

public class MailServiceTest {

    @Test
    public void sendEmailTest() {
        EmailMessage message = new EmailMessage();
        message.setMessage("It's test message");
        message.setReceiver("antonovich.01@mail.ru");
        message.setTitle("Test");

        MailService mailService = new MailService();
        try {
            mailService.sendEmail(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
