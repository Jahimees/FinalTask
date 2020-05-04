package by.epam.ft.command;

import by.epam.ft.dao.VacancyDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Map;

import static by.epam.ft.constant.PageConstant.ANALYTICS_PAGE;

public class FilterAnalyticsCommand implements ActionCommand {

    private static final Logger logger = Logger.getLogger(FilterAnalyticsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String sinceDate = request.getParameter("since_date");
        String toDate = request.getParameter("to_date");

        Map<String, Integer> topVacancies;

        if (sinceDate.equals("") || toDate.equals("")) {
            topVacancies = new VacancyDAO().takePopularVacancies();
        } else {
            topVacancies = new VacancyDAO().takePopularVacancies(LocalDate.parse(sinceDate), LocalDate.parse(toDate));
        }

        response.setContentType("application/javascript");
        response.setCharacterEncoding("utf-8");
        try {
            PrintWriter out = response.getWriter();

            out.print("google.visualization.arrayToDataTable([");
            out.print("['Название', 'Значение'],");
            for (Map.Entry<String, Integer> entry : topVacancies.entrySet()) {
                out.print("['" + entry.getKey() + "', " + entry.getValue() + "],");
            }
            out.print("]);");
            out.flush();
        } catch (IOException e) {
            logger.error("Cannot get writer from response", e);
        }
        return ANALYTICS_PAGE;
    }
}
