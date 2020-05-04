package by.epam.ft.command;

import by.epam.ft.dao.AccountDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.epam.ft.constant.PageConstant.MAIN_PAGE;

public class ReceiveConfirmEmail implements ActionCommand {

    private static final Logger logger = Logger.getLogger(ReceiveConfirmEmail.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        logger.info("Getting confirmation of account with id " + id);
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.setConfirmEmailStatus(id, true);
        logger.info("Email confirmed successfully!");
        return MAIN_PAGE;
    }
}
