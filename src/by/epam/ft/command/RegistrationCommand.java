package by.epam.ft.command;

import by.epam.ft.cryption.Encryption;
import by.epam.ft.dao.AccountDAO;
import by.epam.ft.entity.Account;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

import static by.epam.ft.constant.AttributeAndParameterConstant.*;
import static by.epam.ft.constant.LogConstant.LOGIN_ALREADY_EXISTS;
import static by.epam.ft.constant.LogConstant.NEW_ACCOUNT_CREATED;
import static by.epam.ft.constant.PageConstant.AUTHORIZATION_PAGE;
import static by.epam.ft.constant.PageConstant.REGISTER_PAGE;
import static by.epam.ft.constant.PreparedConstant.INSERT_INTO_ACCOUNT;
import static by.epam.ft.service.ParamsValidator.validateParams;
import static javax.management.timer.Timer.ONE_DAY;

/**
 * Class-command which register the new user
 * implements ActionCommand interface
 *
 * @see ActionCommand
 */
public class RegistrationCommand implements ActionCommand {

    Logger logger = Logger.getLogger(RegistrationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("New user trying to register...");

        String login = request.getParameter(LOGIN);

        AccountDAO accountDao = new AccountDAO();
        int id = accountDao.showIdAccountByLogin(login);
        if (id != 0) {
            logger.error("User tried register with exists login");
            request.setAttribute(ERROR_MESSAGE, LOGIN_ALREADY_EXISTS);
            return REGISTER_PAGE;
        }

        String password = request.getParameter(PASSWORD);
        password = Encryption.encrypt(password);

        String name = validateParams(request.getParameter(NAME));

        String surname = validateParams(request.getParameter(SURNAME));

        String birthday = request.getParameter(AGE);
        Date birth = Date.valueOf(birthday);
        birth = new Date(birth.getTime() + ONE_DAY);

        String email = validateParams(request.getParameter(MAIL));
        Account account = new Account();
        account.setLogin(login);
        account.setPassword(password);
        account.setName(name);
        account.setSurname(surname);
        account.setBirthday(birth);
        account.setEmail(email);

        AccountDAO dao = new AccountDAO();
        dao.insertInfo(account, INSERT_INTO_ACCOUNT);
        logger.info(NEW_ACCOUNT_CREATED);
        return AUTHORIZATION_PAGE;
    }
}
