package by.epam.ft.command;

import by.epam.ft.cryption.Encryption;
import by.epam.ft.dao.AccountDAO;
import by.epam.ft.dao.HrDAO;
import by.epam.ft.entity.Hr;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.epam.ft.constant.AttributeAndParameterConstant.*;
import static by.epam.ft.constant.LogConstant.INVALID_LOGIN_OR_PASSWORD;
import static by.epam.ft.constant.PageConstant.AUTHORIZATION_PAGE;
import static by.epam.ft.constant.PageConstant.MAIN_PAGE;

/**
 * Сlass-command that authorizes the user if was entered the correct username and password
 * implements ActionCommand interface
 * @see ActionCommand
 */
public class LoginCommand implements ActionCommand {

    private static final Logger logger = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("Logging command was activated...");
        String login = request.getParameter(LOGIN);
        String passwordFromJSP = request.getParameter(PASSWORD);
        passwordFromJSP = Encryption.encrypt(passwordFromJSP);
        AccountDAO accountDAO = new AccountDAO();
        String passwordFromDB = accountDAO.showPasswordByLogin(login);

        if (passwordFromDB.equals(passwordFromJSP)) {
            logger.info("Logging complete successfully!");
            initSession(request, login);
            return MAIN_PAGE;
        }
        logger.warn("Authorize failed!");
        request.setAttribute(ERROR_MESSAGE, INVALID_LOGIN_OR_PASSWORD);
        return AUTHORIZATION_PAGE;
    }

    /**
     * Method, which initializes session attributes
     * It set next attributes: login and idAccount to define user
     * @param request
     * @param login
     */
    private void initSession(HttpServletRequest request, String login) {
        HttpSession session = request.getSession();
        session.setAttribute(LOGIN, login);
        AccountDAO accountDAO = new AccountDAO();
        int idAccount = accountDAO.showIdAccountByLogin(login);
        session.setAttribute(ID, idAccount);
        Hr hr;
        HrDAO hrDAO = new HrDAO();
        hr = hrDAO.showByAccountId(idAccount);
        if (hr != null) {
            session.setAttribute(ROLE, HR);
        } else {
            session.setAttribute(ROLE, CANDIDATE);
        }
    }
}
