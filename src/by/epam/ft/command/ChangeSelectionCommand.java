package by.epam.ft.command;

import by.epam.ft.dao.SelectionDAO;
import by.epam.ft.entity.Selection;
import by.epam.ft.exception.InvalidEnterInformationException;
import by.epam.ft.service.mail.EmailSenderCommon;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

import static by.epam.ft.constant.AttributeAndParameterConstant.*;
import static by.epam.ft.constant.LogConstant.INVALID_ENTER_INFORMATION;
import static by.epam.ft.constant.PreparedConstant.UPDATE_SELECTION;
import static javax.management.timer.Timer.ONE_DAY;

/**
 * Class-command which change parameters of chosen selection
 * implements ActionCommand interface
 *
 * @see ActionCommand
 */
public class ChangeSelectionCommand implements ActionCommand {
    Logger logger = Logger.getLogger(ChangeSelectionCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        SelectionDAO selectionDAO = new SelectionDAO();
        String idSelectionStr = request.getParameter(ID_SELECTION);
        String idHrStr = request.getParameter(ID_HR);
        String status = request.getParameter(STATUS);
        logger.info("Changing selection with id " + idSelectionStr + " by HR with id " + idHrStr);
        String selectionDate = request.getParameter(SELECTION_DATE);
        if (idSelectionStr != null && selectionDAO.checkForExists(Integer.parseInt(idSelectionStr))) {
            int idSelection = Integer.parseInt(idSelectionStr);
            int idHr;
            Date selDate;
            if (idHrStr.equals("")) {
                idHr = 0;
            } else {
                try {
                    idHr = Integer.parseInt(idHrStr);
                } catch (InvalidEnterInformationException ex) {
                    logger.error(INVALID_ENTER_INFORMATION, ex);
                    idHr = 0;
                }
            }
            if (selectionDate.equals("")) {
                selDate = null;
            } else {
                selDate = Date.valueOf(selectionDate);
                selDate = new Date(selDate.getTime() + ONE_DAY);
            }
            Selection selection = new Selection();
            selection.setIdSelection(idSelection);
            selection.setIdHr(idHr);
            selection.setSelectionDate(selDate);
            selection.setStatus(status);
            selectionDAO.updateInfo(selection, UPDATE_SELECTION);
            logger.info("Selection " + idSelection + " was successfully changed");

            new EmailSenderCommon().sendMessageIfSelectionChange(selectionDAO.showById(idSelection));
        }
        OpenAccountCommand command = new OpenAccountCommand();
        page = command.execute(request, response);
        return page;
    }
}
