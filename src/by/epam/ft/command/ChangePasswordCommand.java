package by.epam.ft.command;

import by.epam.ft.cryption.Encryption;
import by.epam.ft.dao.AccountDAO;

import javax.servlet.http.HttpServletRequest;

import static by.epam.ft.constant.AttributeAndParameterConstant.*;
import static by.epam.ft.constant.PageConstant.MAIN_PAGE;

/**
 * Class-command which changes user password
 * implements ActionCommand
 * @see ActionCommand
 */
public class ChangePasswordCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String newPassword = request.getParameter(PASSWORD);
        String oldPassword = request.getParameter(OLD_PASSWORD);
        int idAccount = (int) request.getSession().getAttribute(ID);
        String login = (String) request.getSession().getAttribute(LOGIN);

        AccountDAO accountDAO = new AccountDAO();
        String passwordFromDB = accountDAO.showPasswordByLogin(login);
        oldPassword = Encryption.encrypt(oldPassword);
        if (oldPassword.equals(passwordFromDB)) {
            newPassword = Encryption.encrypt(newPassword);
            accountDAO.changePassword(idAccount, newPassword);
            request.setAttribute(WRONG_PASSWORD, "false");
        } else {
            request.setAttribute(WRONG_PASSWORD, "true");
        }
        page = MAIN_PAGE;
        return page;
    }
}
