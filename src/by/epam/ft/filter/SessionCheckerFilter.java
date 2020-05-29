package by.epam.ft.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.ft.constant.AttributeAndParameterConstant.*;
import static by.epam.ft.constant.PageConstant.*;

/**
 * Filter which check path from request
 * 1) if path common - filter transfers control to the servlet
 * 2) if user not authorized AND camest command not login, register or change locale
 * then filter do redirect to authorize page
 * 3) otherwise - filter transfers control to the servlet
 */
public class SessionCheckerFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String currentPath = request.getServletPath();
        boolean isCommonPath = checkPath(currentPath);
        if (isCommonPath) {
            chain.doFilter(request, response);
            return;
        } else {
            HttpSession session = request.getSession();
            Object id = session.getAttribute(ID);
            String command = request.getParameter(COMMAND);
            if (id == null &&
                    !(command.equals(LOGIN) || (command.equals(CHANGE_LOCALE_S)) || (command.equals(REGISTER)) ||
                            command.equals(RECEIVE_CONFIRM_EMAIL))) {
                response.sendRedirect(AUTHORIZATION_PAGE);
                return;
            } else {
                chain.doFilter(request, response);
                return;
            }

        }

    }

    /**
     * check path for common
     * @param path
     * @return true - if page common, false - otherwise
     */
    private boolean checkPath(String path){
        switch (path) {
            case MAIN_PAGE: return true;
            case CONTACTS_PAGE: return true;
            case AUTHORIZATION_PAGE: return true;
            case ABOUT_PAGE: return true;
            case REGISTER_PAGE: return true;
            default: return false;
        }
    }

    public void init(FilterConfig config) {

    }

}
