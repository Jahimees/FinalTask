package by.epam.ft.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.ft.constant.LogConstant.EMPTY_COMMAND;
import static by.epam.ft.constant.PageConstant.MAIN_PAGE;

/**
 * Class-command that is used when a nonexistent command was received
 * implements ActionCommand interface
 * @see ActionCommand
 */
public class EmptyCommand implements ActionCommand {
    Logger logger = Logger.getLogger(EmptyCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        logger.info(EMPTY_COMMAND);
        String page = MAIN_PAGE;
        return page;
    }
}