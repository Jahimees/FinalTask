package by.epam.ft.service;

import by.epam.ft.dao.*;
import by.epam.ft.entity.*;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Utils {

    private static final Logger logger = Logger.getLogger(Utils.class);

    private Utils() {}

    public static Set<Selection> getSelectionsByCandidateName(String candidateName) {
        logger.info("Searching selections by candidate name: " + candidateName);
        Set<Selection> byCandidateName = new HashSet<>();
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
            logger.info(byCandidateName.size() + " selections was found!");
            return byCandidateName;
        }
        logger.info("Selections not found!");
        return byCandidateName;
    }

    public static Set<Selection> getSelectionsByHrName(String hrName) {
        logger.info("Searching selecitons by hr name: " + hrName);
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
                logger.info(byHrName.size() + " selections found!");
                return byHrName;
            }
        } else {
            byHrName.addAll(selectionDAO.showSelectionsWithoutHr());
        }
        logger.info(byHrName.size() + " selections found!");
        return byHrName;
    }

    public static Set<Selection> getSelectionsByStatus(String status) {
        logger.info("Searching selections by status: " + status);
        Set<Selection> byStatus = new HashSet<>();
        SelectionDAO dao = new SelectionDAO();
        byStatus.addAll(dao.showSelections(status));
        logger.info(byStatus.size() + " selections found!");
        return byStatus;
    }

    public static Set<Selection> getSelectionByVacancy(String vacancyName) {
        logger.info("Searching selections by vacancy name " + vacancyName);
        Set<Selection> byVacancy = new HashSet<>();
        VacancyDAO vacancyDAO = new VacancyDAO();
        SelectionDAO selectionDAO = new SelectionDAO();
        List<Vacancy> vacancies = vacancyDAO.showByName(vacancyName);
        for (Vacancy vacancy : vacancies) {
            byVacancy.addAll(selectionDAO.showSelectionsByIdVacancy(vacancy.getIdVacancy()));
        }
        logger.info(byVacancy.size() + " selections found!");
        return byVacancy;
    }

    public static Set<Selection> getSelectionsByDate(String date, boolean isRegistration) {
        logger.info("Searching selections by date: " + date);
        Set<Selection> selections = new HashSet<>();
        SelectionDAO selectionDAO = new SelectionDAO();
        selections.addAll(selectionDAO.showSelectionsByDate(date, isRegistration));
        logger.info(selections.size() + " selections found!");
        return selections;
    }
}
