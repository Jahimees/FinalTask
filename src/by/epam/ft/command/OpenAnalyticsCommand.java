package by.epam.ft.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.epam.ft.constant.AttributeAndParameterConstant.*;
import static by.epam.ft.constant.PageConstant.ANALYTICS_PAGE;
import static by.epam.ft.constant.PageConstant.MAIN_PAGE;

public class OpenAnalyticsCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Object id = session.getAttribute(ID);
        String role = (String) session.getAttribute(ROLE);
        if (!role.equals(HR)) {
            return MAIN_PAGE;
        }

        request.setAttribute(ID_ACC, id);
        request.setAttribute(ROLE, role);

        return ANALYTICS_PAGE;
    }
}
