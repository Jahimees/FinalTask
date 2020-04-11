package by.epam.ft.command;

import by.epam.ft.dao.SelectionDAO;
import by.epam.ft.entity.Selection;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.epam.ft.constant.AttributeAndParameterConstant.ID;
import static by.epam.ft.constant.AttributeAndParameterConstant.ID_SELECTION;
import static by.epam.ft.constant.LogConstant.APPLICATION_NO_LONGER_EXISTS;
import static by.epam.ft.constant.LogConstant.VACANCY_WAS_REVOKED;
import static by.epam.ft.constant.PreparedConstant.DELETE_SELECTION_BY_ID;

/**
 * Class-command which revoke user vacancy
 * implements ActionCommand interface
 * @see ActionCommand
 */
public class RevokeVacancyCommand implements ActionCommand {

    Logger logger = Logger.getLogger(RevokeVacancyCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("Someone revokes vacancy");
        String page = null;
        HttpSession session = request.getSession();
        Object idSession = session.getAttribute(ID);
        OpenAccountCommand command = new OpenAccountCommand();
        SelectionDAO selectionDAO = new SelectionDAO();
        Object idSelection = request.getParameter(ID_SELECTION);
        if (selectionDAO.checkForExists(Integer.parseInt(idSelection.toString()))) {
            Selection selection = new Selection();
            selection.setIdSelection(Integer.parseInt(idSelection.toString()));
            selectionDAO.deleteInfo(selection, DELETE_SELECTION_BY_ID);
            page = command.execute(request);
            logger.info(VACANCY_WAS_REVOKED);
        } else {
            page = command.execute(request);
            logger.warn(APPLICATION_NO_LONGER_EXISTS);
        }
        return page;
    }
}
