package by.epam.ft.command;

import by.epam.ft.dao.VacancyDAO;
import by.epam.ft.entity.Vacancy;
import by.epam.ft.service.mail.EmailSenderCommon;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.epam.ft.constant.AttributeAndParameterConstant.ID_VACANCY;
import static by.epam.ft.constant.LogConstant.VACANCY_WAS_CLOSE;
import static by.epam.ft.constant.PreparedConstant.CLOSE_VACANCY_BY_ID;

/**
 * Class-command which delete chosen vacancy from database
 * implements ActionCommand interface
 *
 * @see ActionCommand
 */
public class CloseVacancyCommand implements ActionCommand {

    private final Logger logger = Logger.getLogger(CloseVacancyCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int idVacancy = Integer.parseInt(request.getParameter(ID_VACANCY));
        logger.info("Closing vacancy with id " + idVacancy);

        Vacancy vacancy = new Vacancy();
        vacancy.setIdVacancy(idVacancy);
        VacancyDAO vacancyDAO = new VacancyDAO();
        vacancyDAO.updateStatus(vacancy, CLOSE_VACANCY_BY_ID);
        logger.info(idVacancy + VACANCY_WAS_CLOSE);

        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("<h1 style=\"text-align: center;\">Good day!</h1>")
                .append("<p style=\"font-size: 25px; font-family: 'Courier new'\">You received this email because you was registered to vacancy which closing now</p>")
                .append("<p style=\"font-size: 25px; font-family: 'Courier new'\">You can check it in your account.</p>")
                .append("<p style=\"font-size: 25px; font-family: 'Courier new'\">If you waiting for interview just don't care. We will interview you</p>")
                .append("<p style=\"font-size: 25px; font-family: 'Courier new'\">If you just sent request to vacancy we so sorry for this situation. Wait when vacancy will open again</p>")
                .append("<p style=\"font-size: 25px; font-family: 'Arial'\">Good luck! Regards It Road Company Inc</p>");

        new EmailSenderCommon().sendMessagesIfActionWithVacancy(idVacancy, "Closing vacancy", messageBuilder);

        LoadOpenedVacancyPageCommand command = new LoadOpenedVacancyPageCommand();
        return command.execute(request, response);
    }

}
