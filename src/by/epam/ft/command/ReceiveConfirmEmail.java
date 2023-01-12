package by.epam.ft.command;

import by.epam.ft.dao.AccountDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.epam.ft.constant.PageConstant.MAIN_PAGE;

public class ReceiveConfirmEmail implements ActionCommand {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.setConfirmEmailStatus(id, true);
        return MAIN_PAGE;
    }
}
