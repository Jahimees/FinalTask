package by.epam.ft.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.epam.ft.constant.PageConstant.MAIN_PAGE;

/**
 * Class-command that is used when a nonexistent command was received
 * implements ActionCommand interface
 *
 * @see ActionCommand
 */
public class EmptyCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = MAIN_PAGE;
        return page;
    }
}