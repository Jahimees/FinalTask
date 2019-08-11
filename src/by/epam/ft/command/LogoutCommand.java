package by.epam.ft.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.epam.ft.constant.AttributeAndParameterConstant.*;
import static by.epam.ft.constant.PageConstant.MAIN_PAGE;

/**
 * Class-command that resets the attributes of the session user
 * This means that the user logs out
 * implements ActionCommand interface
 * @see ActionCommand
 */
public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        session.removeAttribute(LOGIN);
        session.removeAttribute(ID);
        page = MAIN_PAGE;
        return page;
    }
}
