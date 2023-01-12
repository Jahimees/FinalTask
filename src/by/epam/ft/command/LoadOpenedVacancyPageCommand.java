package by.epam.ft.command;

import by.epam.ft.dao.CandidateDAO;
import by.epam.ft.dao.SelectionDAO;
import by.epam.ft.entity.Candidate;
import by.epam.ft.entity.Selection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.epam.ft.constant.AttributeAndParameterConstant.*;
import static by.epam.ft.constant.PageConstant.OPENED_VACANCY_PAGE;

/**
 * Class-command which load all info on vacancy page
 * and transfer info about vacancies for which the user is registered
 * implements ActionCommand interface
 *
 * @see ActionCommand
 */
public class LoadOpenedVacancyPageCommand implements ActionCommand {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        HttpSession session = request.getSession();
        Object id = session.getAttribute(ID);

        CandidateDAO candidateDAO = new CandidateDAO();
        Candidate candidate = candidateDAO.showByAccountId((int) id);
        request.setAttribute(HR, "false");
        if (candidate != null) {
            SelectionDAO selectionDAO = new SelectionDAO();
            List<Selection> selections = selectionDAO.showSelections(candidate.getIdCandidate(), false);

            if (selections.size() != 0) {
                int[] idVacancies = pullIdVacancies(selections);
                request.setAttribute(CHECKED_VACANCIES, idVacancies);
            }
        } else {
            request.setAttribute(HR, "true");
        }
        page = OPENED_VACANCY_PAGE;
        return page;

    }

    /**
     * Takes all vacancy IDs for which the user is registered
     *
     * @param selections
     * @return
     */
    private int[] pullIdVacancies(List<Selection> selections) {
        int[] idVacancies = new int[selections.size()];
        for (int i = 0; i < selections.size(); i++) {
            idVacancies[i] = selections.get(i).getIdVacancy();
        }
        return idVacancies;
    }
}
