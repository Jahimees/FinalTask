package by.epam.ft.command;

import by.epam.ft.dao.AccountDAO;
import by.epam.ft.dao.CandidateDAO;
import by.epam.ft.dao.SelectionDAO;
import by.epam.ft.entity.Account;
import by.epam.ft.entity.Candidate;
import by.epam.ft.entity.Selection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.epam.ft.constant.AttributeAndParameterConstant.*;
import static by.epam.ft.constant.PageConstant.ACCOUNT_HR_PAGE;
import static by.epam.ft.constant.PageConstant.ACCOUNT_PAGE;
import static by.epam.ft.constant.PreparedConstant.GET_ACCOUNT;
import static by.epam.ft.constant.PreparedConstant.GET_CANDIDATE_VACANCIES;

/**
 * Class-command which load all info on Account page
 * Also class define user-role
 * implements ActionCommand interface
 * @see ActionCommand
 */
public class OpenAccountCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        Object id = session.getAttribute(ID);
        String role = (String) session.getAttribute(ROLE);
        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.showById((int) id, GET_ACCOUNT);
        request.setAttribute(ID_ACC, account.getIdAccount());
        request.setAttribute(LOGIN, account.getLogin());
        request.setAttribute(NAME, account.getName());
        request.setAttribute(SURNAME, account.getSurname());
        request.setAttribute(MAIL, account.getEmail());
        request.setAttribute(BIRTHDAY, account.getBirthday().toString());

        if (role.equals(HR)) {
            page = ACCOUNT_HR_PAGE;
            return page;
        } else {
            CandidateDAO candidateDAO = new CandidateDAO();
            Candidate candidate = candidateDAO.showByAccountId((int) id);
            SelectionDAO selectionDAO = new SelectionDAO();
            List<Selection> selections = selectionDAO.showSelections(candidate.getIdCandidate(), GET_CANDIDATE_VACANCIES);
            request.setAttribute(SELECTIONS, selections);
            page = ACCOUNT_PAGE;
            return page;
        }
    }
}


