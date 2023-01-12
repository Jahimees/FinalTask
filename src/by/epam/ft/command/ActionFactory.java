package by.epam.ft.command;

import by.epam.ft.exception.UnknownCommandException;

import javax.servlet.http.HttpServletRequest;

import static by.epam.ft.constant.AttributeAndParameterConstant.COMMAND;
import static by.epam.ft.constant.AttributeAndParameterConstant.WRONG_ACTION;

/**
 * A class that defines a command that comes from
 * a request and is converted to one of the objects of the command class
 */
public class ActionFactory {

    /**
     * define command by request
     * @param request
     * @return heir of ActionCommand interface
     */
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();

        String action = request.getParameter(COMMAND);
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (UnknownCommandException e) {
            request.setAttribute(WRONG_ACTION, action);
        }
        return current;
    }
}
