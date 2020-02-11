package by.epam.ft.command;

import by.epam.ft.action.Utils;
import by.epam.ft.dao.AccountDAO;
import by.epam.ft.dao.CandidateDAO;
import by.epam.ft.dao.HrDAO;
import by.epam.ft.dao.SelectionDAO;
import by.epam.ft.entity.Account;
import by.epam.ft.entity.Candidate;
import by.epam.ft.entity.Hr;
import by.epam.ft.entity.Selection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

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

        request.setAttribute("filter_list", filterChecking(request));
        request.setAttribute("request_filter", request.getParameter("request_filter"));
        if (role.equals(HR)) {
            page = ACCOUNT_HR_PAGE;
            return page;
        } else {
            CandidateDAO candidateDAO = new CandidateDAO();
            Candidate candidate = candidateDAO.showByAccountId((int) id);
            SelectionDAO selectionDAO = new SelectionDAO();
            List<Selection> selections = selectionDAO.showSelections(candidate.getIdCandidate(), false);
            request.setAttribute(SELECTIONS, selections);
            page = ACCOUNT_PAGE;
            return page;
        }
    }

    public List<Selection> filterChecking(HttpServletRequest request) {
        String hrName = request.getParameter("hr_name");
        String requestId = request.getParameter("request_id");
        String candidateName = request.getParameter("candidate_name");
        String status = request.getParameter("status");

        List<Selection> selections = new ArrayList<>();
        Set<Selection> byHrName = new HashSet<>();
        Set<Selection> byRequestId = new HashSet<>();
        Set<Selection> byCandidateName = new HashSet<>();
        Set<Selection> byStatus = new HashSet<>();
        Set<Selection> returnable = new HashSet<>();

       byCandidateName = Utils.getSelectionsByCandidateName(candidateName);
       if (hrName != null) {
           byHrName = Utils.getSelectionsByHrName(hrName);
       }
       if (status != null) {
           byStatus = Utils.getSelectionsByStatus(status);
       }
       if (byCandidateName.size() != 0) {
           returnable = byCandidateName;
       }
       if (byHrName.size() != 0) {

           if (returnable.size() != 0) {
               returnable.retainAll(byHrName);
           } else {
               returnable = byHrName;
           }
       }
       if (byStatus.size() != 0) {
           if (returnable.size() != 0) {
               returnable.retainAll(byStatus);
           } else {
               returnable = byStatus;
           }
       }
//
//        if (byCandidateName.size() != 0 && byHrName.size() != 0) {
//            byCandidateName.retainAll(byHrName);
//            selections.addAll(byCandidateName);
//        } else {
//            if (byCandidateName.size() == 0) {
//                selections.addAll(byHrName);
//            } else {
//                selections.addAll(byCandidateName);
//            }
//        }
        selections.addAll(returnable);
        System.out.println(selections);
        if (selections.size() == 0) {
            return null;
        } else {
            return selections;
        }
        //return null;
    }
}



