package by.epam.ft.servlet;

import by.epam.ft.command.ActionCommand;
import by.epam.ft.command.ActionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.ft.constant.PageConstant.MAIN_PAGE;

/**
 * Servlet which realise pattern MVC
 * all requests comes here.
 * The controller defines a command using the command hierarchy classes and executes it
 */
public class ControllerServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        page = command.execute(request);
        if (page != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = MAIN_PAGE;
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}
