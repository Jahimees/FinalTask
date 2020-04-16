package by.epam.ft.command;

import by.epam.ft.dao.AccountDAO;
import by.epam.ft.dao.CandidateDAO;
import by.epam.ft.dao.VacancyDAO;
import by.epam.ft.entity.Account;
import by.epam.ft.entity.Candidate;
import by.epam.ft.entity.EmailMessage;
import by.epam.ft.entity.Vacancy;
import by.epam.ft.service.mail.MailService;
import org.apache.log4j.Logger;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static by.epam.ft.constant.AttributeAndParameterConstant.ID_VACANCY;
import static by.epam.ft.constant.LogConstant.VACANCY_WAS_CLOSE;
import static by.epam.ft.constant.PreparedConstant.CLOSE_VACANCY_BY_ID;

/**
 * Class-command which delete chosen vacancy from database
 * implements ActionCommand interface
 * @see ActionCommand
 */
public class CloseVacancyCommand implements ActionCommand {

    private final Logger logger = Logger.getLogger(CloseVacancyCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        int idVacancy = Integer.parseInt(request.getParameter(ID_VACANCY));
        logger.info("Closing vacancy with id " + idVacancy);

        Vacancy vacancy = new Vacancy();
        vacancy.setIdVacancy(idVacancy);
        VacancyDAO vacancyDAO = new VacancyDAO();
        vacancyDAO.updateStatus(vacancy, CLOSE_VACANCY_BY_ID);
        logger.info(idVacancy + VACANCY_WAS_CLOSE);

        sendMessages(idVacancy);

        LoadOpenedVacancyPageCommand command = new LoadOpenedVacancyPageCommand();
        return command.execute(request);
    }

    private void sendMessages(int idVacancy) {
        logger.info("Sending messages to accounts...");
        CandidateDAO candidateDAO = new CandidateDAO();
        AccountDAO accountDAO = new AccountDAO();

        List<Candidate> candidateList = candidateDAO.showCandidatesByVacancyId(idVacancy);
        List<Account> accountList = new ArrayList<>();
        for (Candidate candidate: candidateList) {
            accountList.add(accountDAO.showByIdUser(candidate.getIdCandidate(), false));
        }

        EmailMessage message = new EmailMessage();
        message.setTitle("Closing vacancy");
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("<h1 style=\"text-align: center;\">Good day!</h1>")
                .append("<p style=\"font-size: 25px; font-family: 'Courier new'\">You received this email because you was registered to vacancy which closing now</p>")
                .append("<p style=\"font-size: 25px; font-family: 'Courier new'\">You can check it in your account.</p>")
                .append("<p style=\"font-size: 25px; font-family: 'Courier new'\">If you waiting for interview just don't care. We will interview you</p>")
                .append("<p style=\"font-size: 25px; font-family: 'Courier new'\">If you just sent request to vacancy we so sorry for this situation. Wait when vacancy will open again</p>")
                .append("<p style=\"font-size: 25px; font-family: 'Arial'\">Good luck! Regards It Road Company Inc</p>");
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
