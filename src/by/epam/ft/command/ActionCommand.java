package by.epam.ft.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface for commands which coming from requests
 */
public interface ActionCommand {
    /**
     * Execute command
     *
     * @param request
     * @return the page to which the user will be redirected
     */
    String execute(HttpServletRequest request, HttpServletResponse response);
}