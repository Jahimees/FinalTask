package by.epam.ft.dao;

import by.epam.ft.connection.ConnectionPool;
import by.epam.ft.entity.Selection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.ft.constant.AttributeAndParameterConstant.*;
import static by.epam.ft.constant.PreparedConstant.*;

/**
 * manages data in the Selection table
 */
public class SelectionDAO implements DAO<Selection> {


    /**
     * @see DAO
     * @param id
     * @return
     */
    @Override
    public Selection showById(int id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(GET_SELECTION_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Selection selection = new Selection();
                setSelectionParams(selection, resultSet);
                return selection;
            }
        } catch (SQLException e) {
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
        return null;
    }

    public List<Selection> showSelections(String status) {
        List<Selection> selections = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_SELECTION_BY_STATUS);
            statement.setString(1, status);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Selection selection = new Selection();
                setSelectionParams(selection, rs);
                selections.add(selection);
            }
        } catch (SQLException e) {
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
        return selections;
    }

    /**
     * Shows all vacancies which candidate register for
     * @param idHrOrCandidate
     * @return
     */
    public List<Selection> showSelections(int idHrOrCandidate, boolean isHr) {
        List<Selection> selections = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement;
            if (isHr) {
                statement = connection.prepareStatement(GET_HR_VACANCIES);
            } else {
                statement = connection.prepareStatement(GET_CANDIDATE_VACANCIES);
            }
            statement.setInt(1, idHrOrCandidate);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Selection selection = new Selection();
                setSelectionParams(selection, rs);
                selections.add(selection);
            }
        } catch (SQLException e) {
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
        return selections;
    }

    public List<Selection> showSelectionsWithoutHr() {
        List<Selection> selections = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_SELECTION_WITHOUT_HR);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Selection selection = new Selection();
                setSelectionParams(selection, resultSet);
                selections.add(selection);
            }
        } catch (SQLException e) {
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
        return selections;
    }

    /**
     * Shows all selections by idVacancy
     * @param idVacancy
     * @return List<Selection>
     */
    public List<Selection> showSelectionsByIdVacancy(int idVacancy) {
        List<Selection> selections = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_SELECTION_BY_VACANCY);
            statement.setInt(1, idVacancy);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Selection selection = new Selection();
                setSelectionParams(selection, rs);
                selections.add(selection);
            }
        } catch (SQLException e) {
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
        return selections;
    }

    public List<Selection> showSelectionsByIdVacancyAndStatus(int idVacancy, String status) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        List<Selection> resultList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_SELECTIONS_BY_VACANCY_AND_STATUS);
            statement.setInt(1, idVacancy);
            statement.setString(2, status);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Selection selection = new Selection();
                setSelectionParams(selection, resultSet);
                resultList.add(selection);
            }
            return resultList;
        } catch (SQLException e) {
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
        return resultList;
    }

    /**
     * Check selection for exists by candidate id and vacancy id
     * @param idCandidate
     * @param idVacancy
     * @return true - if exists, otherwise - false;
     */
    public boolean checkForExists(int idCandidate, int idVacancy) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(GET_SELECTION_BY_IDS);
            preparedStatement.setInt(1, idCandidate);
            preparedStatement.setInt(2, idVacancy);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }
            return false;
        } catch (SQLException e) {
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
        return false;
    }

    /**
     * Check selection for exists
     * @param idSelection
     * @return true if exists. false - otherwise
     */
    public boolean checkForExists(int idSelection) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(GET_SELECTION_BY_ID);
            preparedStatement.setInt(1, idSelection);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }
            return false;
        } catch (SQLException e) {
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
        return false;
    }

    /**
     * Show all selections from database
     * @return List<Selection>
     */
    @Override
    public List<Selection> showAll() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        List<Selection> resultList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SELECTION);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Selection selection = new Selection();
                setSelectionParams(selection, resultSet);
                resultList.add(selection);
            }
        } catch (SQLException e) {
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
        return resultList;
    }

    /**
     * Change parameters of chosen selection
     * @param selection
     * @param query
     * @return true - if successful, otherwise - false;
     */
    public boolean updateInfo(Selection selection, String query) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean result = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, selection.getIdHr());
            preparedStatement.setDate(2, selection.getSelectionDate());
            preparedStatement.setString(3, selection.getStatus());
            preparedStatement.setInt(4, selection.getIdSelection());

            result = preparedStatement.execute();
        } catch (SQLException e) {
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
        return result;
    }


    /**
     * delete info about chosen selection
     * @param selection
     * @param query
     * @return true - if successful, false - otherwise
     */
    public boolean deleteInfo(Selection selection, String query) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean result = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, selection.getIdSelection());
            result = preparedStatement.execute();
        } catch (SQLException e) {
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
        return result;
    }

    /**
     * creates new selection in database
     * @param selection
     * @param query
     * @return true - if successful, false - otherwise
     */
    public boolean insertInfo(Selection selection, String query) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean result = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, selection.getIdCandidate());
            preparedStatement.setInt(2, selection.getIdVacancy());
            preparedStatement.setString(3, selection.getStatus());
            preparedStatement.setDate(4, selection.getRegistrationDate());
            result = preparedStatement.execute();
        } catch (SQLException e) {
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
        return result;
    }

    public List<Selection> showSelectionsByDate(String date, boolean isRegistration) {
        List<Selection> selections = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement;
            if (isRegistration) {
                preparedStatement = connection.prepareStatement(GET_SELECTION_BY_REGISTRATION_DATE);
            } else {
                preparedStatement = connection.prepareStatement(GET_SELECTION_BY_DATE);
            }
            preparedStatement.setString(1, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Selection selection = new Selection();
                setSelectionParams(selection, resultSet);
                selections.add(selection);
            }
        } catch (SQLException e) {
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
        return  selections;
    }

    public Map<String, Integer> showHrStatistics() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        Map<String, Integer> resultMap = new HashMap<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_HR_STATISCTIS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int count = resultSet.getInt(1);
                String name = resultSet.getString(NAME);
                String surname = resultSet.getString(SURNAME);
                int idHr = resultSet.getInt(ID_HR);

                String key = name + " " + surname + " id: " + idHr;
                resultMap.put(key, count);
            }
        } catch (SQLException e) {
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
        return resultMap;
    }

    public Map<String, Integer> showHrStatistics(LocalDate sinceDate, LocalDate toDate) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        Map<String, Integer> resultMap = new HashMap<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_HR_STATISTICS_WITH_RANGE);
            preparedStatement.setString(1, sinceDate.toString());
            preparedStatement.setString(2, toDate.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int idHr = resultSet.getInt(ID_HR);
                int count = resultSet.getInt(1);
                String name = resultSet.getString(NAME);
                String surname = resultSet.getString(SURNAME);
                String key = name + " " + surname + " id: " + idHr;
                resultMap.put(key, count);
            }
        } catch (SQLException e) {
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
        return resultMap;
    }

    public Map<String, Integer> showPassedStatistic(boolean isPassed) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        Map<String, Integer> result = new HashMap<>();
        try {
            PreparedStatement preparedStatement;
            if (!isPassed) {
                preparedStatement = connection.prepareStatement(FAILED_SELECTIONS);
            } else {
                preparedStatement = connection.prepareStatement(PASSED_SELECTIONS);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String vacancyName = resultSet.getString(1);
                int count = resultSet.getInt(2);
                result.put(vacancyName, count);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Integer> showPassedStatistic(LocalDate sinceDate, LocalDate toDate, boolean isPassed) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        Map<String, Integer> result = new HashMap<>();
        try {
            PreparedStatement preparedStatement;
            if (!isPassed) {
                preparedStatement = connection.prepareStatement(FAILED_SELECTIONS_RANGE);
            } else {
                preparedStatement = connection.prepareStatement(PASSED_SELECTIONS_RANGE);
            }
            preparedStatement.setString(1, sinceDate.toString());
            preparedStatement.setString(2, toDate.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String vacancyName = resultSet.getString(1);
                int count = resultSet.getInt(2);
                result.put(vacancyName, count);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void setSelectionParams(Selection targetSelection, ResultSet resultSet) throws SQLException {
        targetSelection.setIdSelection(resultSet.getInt(ID_SELECTION));
        targetSelection.setIdCandidate(resultSet.getInt(ID_CANDIDATE));
        targetSelection.setIdHr(resultSet.getInt(ID_HR));
        targetSelection.setStatus(resultSet.getString(STATUS));
        targetSelection.setSelectionDate(resultSet.getDate(SELECTION_DATE));
        targetSelection.setIdVacancy(resultSet.getInt(ID_VACANCY));
        targetSelection.setRegistrationDate(resultSet.getDate(REGISTRATION_DATE));
    }
}
