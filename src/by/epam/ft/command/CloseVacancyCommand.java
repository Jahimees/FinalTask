package by.epam.ft.command;

import by.epam.ft.dao.VacancyDAO;
import by.epam.ft.entity.Vacancy;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

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
        String page = null;
        int idVacancy = Integer.parseInt(request.getParameter(ID_VACANCY));
        logger.info("Closing vacancy with id " + idVacancy);

        Vacancy vacancy = new Vacancy();
        vacancy.setIdVacancy(idVacancy);
        VacancyDAO vacancyDAO = new VacancyDAO();
        vacancyDAO.updateInfo(vacancy, CLOSE_VACANCY_BY_ID);
        logger.info(idVacancy + VACANCY_WAS_CLOSE);

        LoadOpenedVacancyPageCommand command = new LoadOpenedVacancyPageCommand();
        page = command.execute(request);
        return page;
    }
}

