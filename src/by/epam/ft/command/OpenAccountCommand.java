package by.epam.ft.command;

import by.epam.ft.dao.AccountDAO;
import by.epam.ft.dao.CandidateDAO;
import by.epam.ft.dao.SelectionDAO;
import by.epam.ft.entity.Account;
import by.epam.ft.entity.Candidate;
import by.epam.ft.entity.Selection;
import by.epam.ft.service.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static by.epam.ft.constant.AttributeAndParameterConstant.*;
import static by.epam.ft.constant.PageConstant.ACCOUNT_HR_PAGE;
import static by.epam.ft.constant.PageConstant.ACCOUNT_PAGE;

/**
 * Class-command which load all info on Account page
 * Also class define user-role
 * implements ActionCommand interface
 *
 * @see ActionCommand
 */
public class OpenAccountCommand implements ActionCommand {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Object id = session.getAttribute(ID);
        String role = (String) session.getAttribute(ROLE);
        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.showById((int) id);
        request.setAttribute(ID_ACC, account.getIdAccount());
        request.setAttribute(LOGIN, account.getLogin());
        request.setAttribute(NAME, account.getName());
        request.setAttribute(SURNAME, account.getSurname());
        request.setAttribute(MAIL, account.getEmail());
        request.setAttribute(BIRTHDAY, account.getBirthday().toString());
        request.setAttribute(RESUME, account.getResume());
        request.setAttribute(IS_CONFIRMED, account.isConfirmed());

        request.setAttribute("filter_list", filterChecking(request));
        if (role.equals(HR)) {
            return ACCOUNT_HR_PAGE;
        } else {
            CandidateDAO candidateDAO = new CandidateDAO();
            Candidate candidate = candidateDAO.showByAccountId((int) id);
            SelectionDAO selectionDAO = new SelectionDAO();
            List<Selection> selections = selectionDAO.showSelections(candidate.getIdCandidate(), false);
            request.setAttribute(SELECTIONS, selections);
            return ACCOUNT_PAGE;
        }
    }

    public List<Selection> filterChecking(HttpServletRequest request) {
        String hrName = request.getParameter("hr_name");
        String candidateName = request.getParameter("candidate_name");
        String status = request.getParameter("status");
        String vacancyName = request.getParameter("vacancy_name");
        String selectionDate = request.getParameter("selection_date");
        String registrationDate = request.getParameter("registration_date");

        boolean ignoreByHr = true;
        boolean ignoreByCandidate = true;
        boolean ignoreByStatus = true;
        boolean ignoreByVacancy = true;
        boolean ignoreBySelectionDate = true;
        boolean ignoreByRegistrationDate = true;

        Set<Selection> byHrName = new HashSet<>();
        Set<Selection> byCandidateName = new HashSet<>();
        Set<Selection> byStatus = new HashSet<>();
        Set<Selection> byVacancyName = new HashSet<>();
        Set<Selection> bySelectionDate = new HashSet<>();
        Set<Selection> byRegistrationDate = new HashSet<>();
        Set<Selection> returnable = new HashSet<>();


        if (candidateName != null && !candidateName.equals("")) {
            byCandidateName = Utils.getSelectionsByCandidateName(candidateName);
            returnable = byCandidateName;
            ignoreByCandidate = false;
        }
        if (hrName != null && !hrName.equals("")) {
            byHrName = Utils.getSelectionsByHrName(hrName);
            returnable = byHrName;
            ignoreByHr = false;
        }
        if (status != null && !status.equals("")) {
            byStatus = Utils.getSelectionsByStatus(status);
            returnable = byStatus;
            ignoreByStatus = false;
        }
        if (vacancyName != null && !vacancyName.equals("")) {
            byVacancyName = Utils.getSelectionByVacancy(vacancyName);
            returnable = byVacancyName;
            ignoreByVacancy = false;
        }
        if (selectionDate != null && !selectionDate.equals("")) {
            bySelectionDate = Utils.getSelectionsByDate(selectionDate, false);
            returnable = bySelectionDate;
            ignoreBySelectionDate = false;
        }
        if (registrationDate != null && !registrationDate.equals("")) {
            byRegistrationDate = Utils.getSelectionsByDate(registrationDate, true);
            returnable = byRegistrationDate;
            ignoreByRegistrationDate = false;
        }

        if (!ignoreByCandidate) {
            returnable.retainAll(byCandidateName);
        }
        if (!ignoreByHr) {
            returnable.retainAll(byHrName);
        }
        if (!ignoreByStatus) {
            returnable.retainAll(byStatus);
        }
        if (!ignoreByVacancy) {
            returnable.retainAll(byVacancyName);
        }
        if (!ignoreBySelectionDate) {
            returnable.retainAll(bySelectionDate);
        }
        if (!ignoreByRegistrationDate) {
            returnable.retainAll(byRegistrationDate);
        }

        List<Selection> selections = new ArrayList<>(returnable);
        if (selections.size() == 0) {
            return null;
        } else {
            return selections;
        }
    }
}
