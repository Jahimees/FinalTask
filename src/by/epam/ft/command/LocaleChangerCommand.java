package by.epam.ft.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.epam.ft.constant.AttributeAndParameterConstant.*;
import static by.epam.ft.constant.PageConstant.MAIN_PAGE;

/**
 * Class-command which change pages language
 * implements ActionCommand interface
 *
 * @see ActionCommand
 */
public class LocaleChangerCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        HttpSession session = request.getSession(true);
        String language = request.getParameter(LOCALE).substring(0, request.getParameter(LOCALE).indexOf("_"));
        String country = request.getParameter(LOCALE).substring(request.getParameter(LOCALE).indexOf("_") + 1);
        session.setAttribute(LOCALE_LANGUAGE, language);
        session.setAttribute(LOCALE_COUNTRY, country);
        page = MAIN_PAGE;
        return page;
    }
}
