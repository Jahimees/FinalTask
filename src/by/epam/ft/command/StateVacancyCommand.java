package by.epam.ft.command;

import by.epam.ft.dao.CandidateDAO;
import by.epam.ft.dao.SelectionDAO;
import by.epam.ft.entity.Candidate;
import by.epam.ft.entity.Selection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.epam.ft.constant.AttributeAndParameterConstant.*;
import static by.epam.ft.constant.PreparedConstant.INSERT_INTO_SELECTION;

/**
 * Class-command which register user to vacancy
 * implements ActionCommand interface
 * @see ActionCommand
 */
public class StateVacancyCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = null;
        Object idSession = session.getAttribute(ID);
        Object idVacancy = request.getParameter(ID_VACANCY);
        CandidateDAO candidateDAO = new CandidateDAO();
        Candidate candidate = candidateDAO.showByAccountId((int) idSession);
        SelectionDAO selectionDAO = new SelectionDAO();
        OpenVacancyCommand command = new OpenVacancyCommand();
        if (selectionDAO.checkForExists(candidate.getIdCandidate(), Integer.parseInt(idVacancy.toString()))) {
            page = command.execute(request);
            return page;
        } else {
            Selection selection = new Selection();
            selection.setIdVacancy(Integer.parseInt(idVacancy.toString()));
            selection.setIdCandidate(candidate.getIdCandidate());
            selection.setStatus(CONSIDERATION);
            selectionDAO.insertInfo(selection, INSERT_INTO_SELECTION);
            page = command.execute(request);
            return page;
        }

    }
}
