package by.epam.ft.command;

import by.epam.ft.dao.SelectionDAO;
import by.epam.ft.dao.VacancyDAO;
import by.epam.ft.entity.CustomSelection;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.ft.constant.PageConstant.ANALYTICS_PAGE;

public class FilterAnalyticsCommand implements ActionCommand {

    private static final Logger logger = Logger.getLogger(FilterAnalyticsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String sinceDate = request.getParameter("since_date");
        String toDate = request.getParameter("to_date");
        String chart = request.getParameter("chart_name");

        Map<String, Integer> statisticsData = new HashMap<>();
        switch (chart) {
            case "topVacanciesStatistics": {
                statisticsData = getTopVacansiesData(sinceDate, toDate);
                break;
            }
            case "hrStatisticChart": {
                statisticsData = getHrStatistics(sinceDate, toDate);
                break;
            }
            case "passedStatistics": {
                List<CustomSelection> customSelections = getPassedStatistic(sinceDate, toDate);
                PrintWriter out = null;
                try {
                    out = response.getWriter();
                } catch (IOException e) {
                    logger.error("Cannot get writer", e);
                }
                out.print("[");
                for (int i = 0; i < customSelections.size(); i++) {
                    CustomSelection customSelection = customSelections.get(i);
                    customSelection.setPercent((double) customSelection.getPassedCount()/
                            (customSelection.getPassedCount()+customSelection.getFailedCount()));

                    out.print("{\"name\": \"" + customSelection.getVacancyName() + "\""
                            + ", \"passedCount\": " + customSelection.getPassedCount()
                            + ", \"failedCount\": " + customSelection.getFailedCount()
                            + ", \"percent\": " + customSelection.getPercent() + "}");
                    if (i < customSelections.size() - 1) {
                        out.print(",");
                    }
                }
                out.print("]");
                out.flush();
                return ANALYTICS_PAGE;
            }
        }

        response.setContentType("application/javascript");
        response.setCharacterEncoding("utf-8");
        try {
            PrintWriter out = response.getWriter();

            out.print("google.visualization.arrayToDataTable([");
            out.print("['Название', 'Значение'],");
            for (Map.Entry<String, Integer> entry : statisticsData.entrySet()) {
                out.print("['" + entry.getKey() + "', " + entry.getValue() + "],");
            }
            out.print("]);");
            out.flush();
        } catch (IOException e) {
            logger.error("Cannot get writer from response", e);
        }

        return ANALYTICS_PAGE;
    }

    private Map<String, Integer> getTopVacansiesData(String sinceDate, String toDate) {
        Map<String, Integer> topVacancies;

        if (sinceDate.equals("") || toDate.equals("")) {
            topVacancies = new VacancyDAO().takePopularVacancies();
        } else {
            topVacancies = new VacancyDAO().takePopularVacancies(LocalDate.parse(sinceDate), LocalDate.parse(toDate));
        }
        return topVacancies;
    }

    private Map<String, Integer> getHrStatistics(String sinceDate, String toDate) {
        Map<String, Integer> hrStatistics;

        if (sinceDate.equals("") || toDate.equals("")) {
            hrStatistics = new SelectionDAO().showHrStatistics();
        } else {
            hrStatistics = new SelectionDAO().showHrStatistics(LocalDate.parse(sinceDate), LocalDate.parse(toDate));
        }

        return hrStatistics;
    }

    private List<CustomSelection> getPassedStatistic(String sinceDate, String toDate) {
        SelectionDAO selectionDAO = new SelectionDAO();
        List<CustomSelection> customSelections;
        Map<String, Integer> passed;
        Map<String, Integer> failed;
        if (sinceDate.equals("") || toDate.equals("")) {
            passed = selectionDAO.showPassedStatistic(true);
            failed = selectionDAO.showPassedStatistic(false);
           customSelections = fillTheMap(passed, failed);
        } else {
            passed = selectionDAO.showPassedStatistic(LocalDate.parse(sinceDate), LocalDate.parse(toDate), true);
            failed = selectionDAO.showPassedStatistic(LocalDate.parse(sinceDate), LocalDate.parse(toDate), false);
            customSelections = fillTheMap(passed, failed);
        }
        return customSelections;
    }

    public List<CustomSelection> fillTheMap(Map<String, Integer> passed, Map<String, Integer> failed) {
        List<CustomSelection> customSelections = new ArrayList<>();
        for (Map.Entry<String, Integer> passedEntry: passed.entrySet()) {
            CustomSelection customSelection = new CustomSelection();
            if (failed.containsKey(passedEntry.getKey())) {
                customSelection.setFailedCount(failed.get(passedEntry.getKey()));
                customSelection.setPassedCount(passedEntry.getValue());
            } else {
                customSelection.setPassedCount(passedEntry.getValue());
                customSelection.setFailedCount(0);
            }
            customSelection.setVacancyName(passedEntry.getKey());

            customSelections.add(customSelection);
        }
        return customSelections;
    }
}
