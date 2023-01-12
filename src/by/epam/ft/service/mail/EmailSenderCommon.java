package by.epam.ft.service.mail;

import by.epam.ft.dao.AccountDAO;
import by.epam.ft.dao.CandidateDAO;
import by.epam.ft.entity.Account;
import by.epam.ft.entity.Candidate;
import by.epam.ft.entity.EmailMessage;
import by.epam.ft.entity.Selection;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

public class EmailSenderCommon {


    public void sendMessagesIfActionWithVacancy(int idVacancy, String title, StringBuilder messageBuilder) {
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
                    mailService.sendEmail(message);
                } catch (MessagingException e) {
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
        }
    }
}
