package by.epam.ft.service.mail;

import by.epam.ft.dao.AccountDAO;
import by.epam.ft.dao.CandidateDAO;
import by.epam.ft.entity.Account;
import by.epam.ft.entity.Candidate;
import by.epam.ft.entity.EmailMessage;
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
}
