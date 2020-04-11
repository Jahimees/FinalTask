package by.epam.ft.command;

import by.epam.ft.entity.EmailMessage;
import by.epam.ft.service.mail.MailService;
import org.apache.log4j.Logger;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static by.epam.ft.constant.PageConstant.MAIN_PAGE;

public class SendRequestConfirmAccountCommand implements ActionCommand {

    Logger logger = Logger.getLogger(SendRequestConfirmAccountCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        try {
            logger.info("Trying to send email for confirm...");
            String email = request.getParameter("email");
            String address = generateAddress(request);

            EmailMessage message = new EmailMessage();
            message.setReceiver(email);
            message.setTitle("Подтвердите email");
            StringBuilder messageBuilder = new StringBuilder();
            messageBuilder.append("<h1 style=\"text-align: center;\">Good day!</h1>")
                    .append("<p style=\"font-size: 25px; font-family: 'Courier new'\">You received this email because you made a request for email confirmation.</p>")
                    .append("<p style=\"font-size: 25px; font-family: 'Courier new'\">If You have not performed any actions on our site, please ignore this email</p>")
                    .append("<p style=\"font-size: 20px; font-family: 'Arial'\">To confirm your email go to the link:</p>")
                    .append("<p style=\"font-size: 20px; font-family: 'Arial'\">http://192.168.56.1:8080/html/controller?command=receive_confirm_email&id=91</p>");
            message.setMessage(messageBuilder.toString());

            MailService mailService = new MailService();
            mailService.sendEmail(message);
            logger.info("Request message was send to " + email + " successfully");
        } catch (UnknownHostException e) {
            logger.error("Unknown host", e);
        } catch (MessagingException e) {
            logger.error("Failed to send email", e);
        }
        return MAIN_PAGE;
    }

    private String generateAddress(HttpServletRequest request) throws UnknownHostException {
        StringBuilder address = new StringBuilder(InetAddress.getLocalHost().getHostAddress())
                .append(":8080/html/controller?command=receive_confirm_email&id=")
                .append(request.getParameter("id"));
        return address.toString();
    }
}
