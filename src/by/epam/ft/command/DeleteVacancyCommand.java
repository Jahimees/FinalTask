package by.epam.ft.command;

import by.epam.ft.dao.*;
import by.epam.ft.entity.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.epam.ft.constant.AttributeAndParameterConstant.ID_VACANCY;
import static by.epam.ft.constant.LogConstant.*;
import static by.epam.ft.constant.PreparedConstant.*;

/**
 * Class-command which delete chosen vacancy from database
 * implements ActionCommand interface
 * @see ActionCommand
 */
public class DeleteVacancyCommand implements ActionCommand {
    private final Logger logger = Logger.getLogger(DeleteVacancyCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        int idVacancy = Integer.parseInt(request.getParameter(ID_VACANCY));

        Vacancy vacancy = new Vacancy();
        vacancy.setIdVacancy(idVacancy);
        VacancyDAO vacancyDAO = new VacancyDAO();
        vacancyDAO.deleteInfo(vacancy, DELETE_VACANCY_BY_ID);
        logger.warn(idVacancy + VACANCY_WAS_DELETED);

        SelectionDAO selectionDAO = new SelectionDAO();
        List<Selection> selectionList = selectionDAO.showSelectionsByIdVacancy(idVacancy);

        for (Selection selection : selectionList) {
            selectionDAO.deleteInfo(selection, DELETE_SELECTION_BY_ID);
            logger.warn(selection.getIdSelection() + SELECTION_WAS_DELETED);
        }

        OpenAccountCommand command = new OpenAccountCommand();
        page = command.execute(request);
        return page;
    }
}

