package by.epam.ft.service;

import by.epam.ft.dao.*;
import by.epam.ft.entity.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Utils {


    private Utils() {}

    public static Set<Selection> getSelectionsByCandidateName(String candidateName) {
        Set<Selection> byCandidateName = new HashSet<>();
        String[] names = candidateName.split(" ");
        AccountDAO dao = new AccountDAO();
        CandidateDAO candidateDAO = new CandidateDAO();
        SelectionDAO selectionDAO = new SelectionDAO();
        List<Account> accountsByNames = dao.showByNameAndSurname(names[0], names[1]);
        if (accountsByNames.size() > 0) {
            for (Account account: accountsByNames) {
                Candidate candidate = candidateDAO.showByAccountId(account.getIdAccount());
                if (candidate == null) {
                    continue;
                }
                byCandidateName.addAll(selectionDAO.showSelections(candidate.getIdCandidate(), false));
            }
            return byCandidateName;
        }
        return byCandidateName;
    }

    public static Set<Selection> getSelectionsByHrName(String hrName) {
        Set<Selection> byHrName = new HashSet<>();
        SelectionDAO selectionDAO = new SelectionDAO();
        if (!hrName.toLowerCase().equals("hr не назначен")) {
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
        } else {
            byHrName.addAll(selectionDAO.showSelectionsWithoutHr());
        }
        return byHrName;
    }

    public static Set<Selection> getSelectionsByStatus(String status) {
        Set<Selection> byStatus = new HashSet<>();
        SelectionDAO dao = new SelectionDAO();
        byStatus.addAll(dao.showSelections(status));
        return byStatus;
    }

    public static Set<Selection> getSelectionByVacancy(String vacancyName) {
        Set<Selection> byVacancy = new HashSet<>();
        VacancyDAO vacancyDAO = new VacancyDAO();
        SelectionDAO selectionDAO = new SelectionDAO();
        List<Vacancy> vacancies = vacancyDAO.showByName(vacancyName);
        for (Vacancy vacancy : vacancies) {
            byVacancy.addAll(selectionDAO.showSelectionsByIdVacancy(vacancy.getIdVacancy()));
        }
        return byVacancy;
    }

    public static Set<Selection> getSelectionsByDate(String date, boolean isRegistration) {
        Set<Selection> selections = new HashSet<>();
        SelectionDAO selectionDAO = new SelectionDAO();
        selections.addAll(selectionDAO.showSelectionsByDate(date, isRegistration));
        return selections;
    }
}
