package by.epam.ft.command;

import by.epam.ft.dao.VacancyDAO;
import by.epam.ft.entity.Vacancy;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.ft.constant.AttributeAndParameterConstant.ID_VACANCY;
import static by.epam.ft.constant.LogConstant.VACANCY_WAS_OPEN;
import static by.epam.ft.constant.PreparedConstant.OPEN_VACANCY_BY_ID;

public class OpenVacancyCommand implements ActionCommand {

    private final Logger logger = Logger.getLogger(OpenVacancyCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        int idVacancy = Integer.parseInt(request.getParameter(ID_VACANCY));

        Vacancy vacancy = new Vacancy();
        vacancy.setIdVacancy(idVacancy);
        VacancyDAO vacancyDAO = new VacancyDAO();
        vacancyDAO.updateStatus(vacancy, OPEN_VACANCY_BY_ID);
        logger.info(idVacancy + VACANCY_WAS_OPEN);

        LoadClosedVacancyPageCommand command = new LoadClosedVacancyPageCommand();
        return command.execute(request);
    }
}
