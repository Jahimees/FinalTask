package by.epam.ft.command;

import by.epam.ft.dao.CandidateDAO;
import by.epam.ft.entity.Candidate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.epam.ft.constant.AttributeAndParameterConstant.HR;
import static by.epam.ft.constant.AttributeAndParameterConstant.ID;
import static by.epam.ft.constant.PageConstant.CLOSED_VACANCY_PAGE;

public class LoadClosedVacancyPageCommand implements ActionCommand {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        CandidateDAO candidateDAO = new CandidateDAO();
        String page = null;

        HttpSession session = request.getSession();
        Object id = session.getAttribute(ID);
        request.setAttribute(HR, "false");

        Candidate candidate = candidateDAO.showByAccountId((int) id);

        if (candidate == null) {
            request.setAttribute(HR, "true");
        }
        page = CLOSED_VACANCY_PAGE;
        return page;
    }
}
