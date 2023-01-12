package by.epam.ft.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.epam.ft.constant.AttributeAndParameterConstant.ID;
import static by.epam.ft.constant.AttributeAndParameterConstant.LOGIN;
import static by.epam.ft.constant.PageConstant.MAIN_PAGE;

/**
 * Class-command that resets the attributes of the session user
 * This means that the user logs out
 * implements ActionCommand interface
 *
 * @see ActionCommand
 */
public class LogoutCommand implements ActionCommand {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        HttpSession session = request.getSession();
        session.removeAttribute(LOGIN);
        session.removeAttribute(ID);
        page = MAIN_PAGE;
        return page;
    }
}
