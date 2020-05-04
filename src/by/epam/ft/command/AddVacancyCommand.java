package by.epam.ft.command;

import by.epam.ft.dao.VacancyDAO;
import by.epam.ft.entity.Vacancy;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.epam.ft.constant.AttributeAndParameterConstant.DESCRIPTION;
import static by.epam.ft.constant.AttributeAndParameterConstant.VACANCY_NAME;
import static by.epam.ft.constant.LogConstant.ADDED_NEW_VACANCY;
import static by.epam.ft.constant.PreparedConstant.INSERT_INTO_VACANCY;
import static by.epam.ft.service.ParamsValidator.validateParams;

/**
 * Class-command which add new vacancy to database
 * implements ActionCommand interface
 *
 * @see ActionCommand
 */
public class AddVacancyCommand implements ActionCommand {
    Logger logger = Logger.getLogger(AddVacancyCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Adding vacancy...");
        String page;
        String vacName = request.getParameter(VACANCY_NAME);
        vacName = validateParams(vacName);
        String description = request.getParameter(DESCRIPTION);
        description = validateParams(description);

        Vacancy vacancy = new Vacancy();
        vacancy.setName(vacName);
        vacancy.setDescription(description);
        VacancyDAO vacancyDAO = new VacancyDAO();
        vacancyDAO.insertInfo(vacancy, INSERT_INTO_VACANCY);
        logger.info(ADDED_NEW_VACANCY);

        OpenAccountCommand command = new OpenAccountCommand();
        page = command.execute(request, response);
        return page;
    }
}
