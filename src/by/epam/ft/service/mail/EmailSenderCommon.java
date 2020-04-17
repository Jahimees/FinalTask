package by.epam.ft.service.mail;

import by.epam.ft.dao.AccountDAO;
import by.epam.ft.dao.CandidateDAO;
import by.epam.ft.entity.Account;
import by.epam.ft.entity.Candidate;
import by.epam.ft.entity.EmailMessage;
import by.epam.ft.entity.Selection;
import org.apache.log4j.Logger;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

public class EmailSenderCommon {

    private final Logger logger = Logger.getLogger(EmailSenderCommon.class);

    public void sendMessagesIfActionWithVacancy(int idVacancy, String title, StringBuilder messageBuilder) {
        logger.info("Sending messages to accounts...");
        CandidateDAO candidateDAO = new CandidateDAO();
        AccountDAO accountDAO = new AccountDAO();

        List<Candidate> candidateList = candidateDAO.showCandidatesByVacancyId(idVacancy);
        List<Account> accountList = new ArrayList<>();
        for (Candidate candidate: candidateList) {
            accountList.add(accountDAO.showByIdUser(candidate.getIdCandidate(), false));
        }

        EmailMessage message = new EmailMessage();
        message.setTitle(title);

        message.setMessage(messageBuilder.toString());

        for (Account account: accountList) {
            if (account.isConfirmed()) {
                message.setReceiver(account.getEmail());

                MailService mailService = new MailService();
                try {
                    logger.info("Sending message to " + account.getEmail());
                    mailService.sendEmail(message);
                } catch (MessagingException e) {
                    logger.error("Cannot send email to " + account.getEmail(), e);
                }
            }
        }
    }

    public void sendMessageIfSelectionChange(Selection selection) {
        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.showByIdUser(selection.getIdCandidate(), false);

        if (!account.isConfirmed()) {
            return;
        }
        logger.info("Sending messages to account " + account.getEmail());

        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setTitle("Request status was changed");

        emailMessage.setReceiver(account.getEmail());

        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("<h1 style=\"text-align: center;\">Good day!</h1>")
                .append("<p style=\"font-size: 25px; font-family: 'Courier new'\">You received this email because yours request status was changed.</p>")
                .append("<p style=\"font-size: 25px; font-family: 'Courier new'\">Check your account please! Id request: " + selection.getIdSelection() + "</p>")
                .append("<p style=\"font-size: 25px; font-family: 'Arial'\">Good luck! Regards It Road Company Inc</p>");

        emailMessage.setMessage(messageBuilder.toString());
        try {
            new MailService().sendEmail(emailMessage);
        } catch (MessagingException e) {
            logger.error("Email cannot be sent");
        }
    }
}
