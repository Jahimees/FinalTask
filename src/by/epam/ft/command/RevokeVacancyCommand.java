package by.epam.ft.command;

import by.epam.ft.dao.SelectionDAO;
import by.epam.ft.entity.Selection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.epam.ft.constant.AttributeAndParameterConstant.ID;
import static by.epam.ft.constant.AttributeAndParameterConstant.ID_SELECTION;
import static by.epam.ft.constant.PreparedConstant.DELETE_SELECTION_BY_ID;

/**
 * Class-command which revoke user vacancy
 * implements ActionCommand interface
 *
 * @see ActionCommand
 */
public class RevokeVacancyCommand implements ActionCommand {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
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
            page = command.execute(request, response);
        } else {
            page = command.execute(request, response);
        }
        return page;
    }
}
