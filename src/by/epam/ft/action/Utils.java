package by.epam.ft.action;

import by.epam.ft.dao.AccountDAO;
import by.epam.ft.dao.CandidateDAO;
import by.epam.ft.dao.HrDAO;
import by.epam.ft.dao.SelectionDAO;
import by.epam.ft.entity.Account;
import by.epam.ft.entity.Candidate;
import by.epam.ft.entity.Hr;
import by.epam.ft.entity.Selection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Utils {
    private Utils() {}

    public static Set<Selection> getSelectionsByCandidateName(String candidateName) {
        Set<Selection> byCandidateName = new HashSet<>();
        if (candidateName != null && candidateName != "") {
            String[] names = candidateName.split(" ");
            AccountDAO dao = new AccountDAO();
            CandidateDAO candidateDAO = new CandidateDAO();
            SelectionDAO selectionDAO = new SelectionDAO();
            List<Account> accountsByNames = dao.showByNameAndSurname(names[0], names[1]);
            if (accountsByNames.size() > 0) {
                for (Account account: accountsByNames) {
                    Candidate candidate = candidateDAO.showByAccountId(account.getIdAccount());
                    byCandidateName.addAll(selectionDAO.showSelections(candidate.getIdCandidate(), false));
                }
                return byCandidateName;
            }
        }
        return byCandidateName;
    }

    public static Set<Selection> getSelectionsByHrName(String hrName) {
        Set<Selection> byHrName = new HashSet<>();
        SelectionDAO selectionDAO = new SelectionDAO();
        if (!hrName.toLowerCase().equals("hr не назначен")) {
            if (!hrName.equals("")) {
                String[] names = hrName.split(" ");
                AccountDAO dao = new AccountDAO();

                HrDAO hrDAO = new HrDAO();
                List<Account> accountsByNames = dao.showByNameAndSurname(names[0], names[1]);
                if (accountsByNames.size() > 0) {
                    for (Account account : accountsByNames) {
                        Hr hr = hrDAO.showByAccountId(account.getIdAccount());
                        byHrName.addAll(selectionDAO.showSelections(hr.getIdHr(), true));
                    }
                    return byHrName;
                }
            }
        } else {
            byHrName.addAll(selectionDAO.showSelectionsWithoutHr());
        }
        return byHrName;
    }

    public static Set<Selection> getSelectionsByStatus(String status) {
        Set<Selection> byStatus = new HashSet<>();
        if (status != null && !status.equals("")) {
            SelectionDAO dao = new SelectionDAO();
            byStatus.addAll(dao.showSelections(status));
        }
        return byStatus;
    }
}
