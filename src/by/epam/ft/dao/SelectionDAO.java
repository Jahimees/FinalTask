package by.epam.ft.dao;

import by.epam.ft.connection.ConnectionPool;
import by.epam.ft.entity.Selection;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.ft.constant.AttributeAndParameterConstant.*;
import static by.epam.ft.constant.LogConstant.SQL_CLOSE_CONNECTION_EXCEPTION;
import static by.epam.ft.constant.LogConstant.SQL_DAO_EXCEPTION;
import static by.epam.ft.constant.PreparedConstant.*;

/**
 * manages data in the Selection table
 */
public class SelectionDAO implements DAO<Selection> {

    private static final Logger logger = Logger.getLogger(SelectionDAO.class);

    /**
     * @see DAO
     * @param id
     * @return
     */
    @Override
    public Selection showById(int id) {
        logger.info("Searching selection by id " + id);
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
            logger.error(SQL_DAO_EXCEPTION, e);
        } finally {
            try {
                logger.info("Closing connection...");
                connection.close();
            } catch (SQLException e) {
                logger.error(SQL_CLOSE_CONNECTION_EXCEPTION, e);
            }
        }
        return null;
    }

    public List<Selection> showSelections(String status) {
        logger.info("Searching selections by status " + status);
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
            logger.error(SQL_DAO_EXCEPTION, e);
        } finally {
            try {
                logger.info("Connection closing...");
                connection.close();
            } catch (SQLException e) {
                logger.error(SQL_CLOSE_CONNECTION_EXCEPTION, e);
            }
        }
        logger.info(selections.size() + " selections found!");
        return selections;
    }

    /**
     * Shows all vacancies which candidate register for
     * @param idHrOrCandidate
     * @return
     */
    public List<Selection> showSelections(int idHrOrCandidate, boolean isHr) {
        logger.info("Searching selection by id hr or candidate...");
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
            logger.error(SQL_DAO_EXCEPTION, e);
        } finally {
            try {
                logger.info("Connection closing...");
                connection.close();
            } catch (SQLException e) {
                logger.error(SQL_CLOSE_CONNECTION_EXCEPTION, e);
            }
        }
        logger.info(selections.size() + " selections found!");
        return selections;
    }

    public List<Selection> showSelectionsWithoutHr() {
        logger.info("Searching selections without hr...");
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
            logger.error(SQL_DAO_EXCEPTION, e);
        } finally {
            try {
                logger.info("Closing connection...");
                connection.close();
            } catch (SQLException e) {
                logger.error(SQL_CLOSE_CONNECTION_EXCEPTION, e);
            }
        }
        logger.info(selections.size() + " selections found!");
        return selections;
    }

    /**
     * Shows all selections by idVacancy
     * @param idVacancy
     * @return List<Selection>
     */
    public List<Selection> showSelectionsByIdVacancy(int idVacancy) {
        logger.info("Searching selections by id vacancy: " + idVacancy);
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
            logger.info(selections.size() + " selections found!");
        } catch (SQLException e) {
            logger.error(SQL_DAO_EXCEPTION, e);
        } finally {
            try {
                logger.info("Connection closing...");
                connection.close();
            } catch (SQLException e) {
                logger.error(SQL_CLOSE_CONNECTION_EXCEPTION, e);
            }
        }
        return selections;
    }

    public List<Selection> showSelectionsByIdVacancyAndStatus(int idVacancy, String status) {
        logger.info("Searching selections by id " + idVacancy + " and status " + status);
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
            logger.info(resultList.size() + " selections found!");
            return resultList;
        } catch (SQLException e) {
            logger.error(SQL_DAO_EXCEPTION, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(SQL_CLOSE_CONNECTION_EXCEPTION, e);
            }
        }
        logger.info("Selections not found!");
        return resultList;
    }

    /**
     * Check selection for exists by candidate id and vacancy id
     * @param idCandidate
     * @param idVacancy
     * @return true - if exists, otherwise - false;
     */
    public boolean checkForExists(int idCandidate, int idVacancy) {
        logger.info("Checking for exists selection by candidate id " + idCandidate + " and id vacancy " + idVacancy);
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
            logger.error(SQL_DAO_EXCEPTION, e);
        } finally {
            try {
                logger.info("Closing connection...");
                connection.close();
            } catch (SQLException e) {
                logger.error(SQL_CLOSE_CONNECTION_EXCEPTION, e);
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
        logger.info("Check for exists by id selection: " + idSelection);
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
            logger.error(SQL_DAO_EXCEPTION, e);
        } finally {
            try {
                logger.info("Closing connection...");
                connection.close();
            } catch (SQLException e) {
                logger.error(SQL_CLOSE_CONNECTION_EXCEPTION, e);
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
        logger.info("Getting all selections...");
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
            logger.error(SQL_DAO_EXCEPTION, e);
        } finally {
            try {
                logger.info("Closing connection...");
                connection.close();
            } catch (SQLException e) {
                logger.error(SQL_CLOSE_CONNECTION_EXCEPTION, e);
            }
        }
        logger.info(resultList.size() + " selections was found!");
        return resultList;
    }

    /**
     * Change parameters of chosen selection
     * @param selection
     * @param query
     * @return true - if successful, otherwise - false;
     */
    public boolean updateInfo(Selection selection, String query) {
        logger.info("Updating selection info...");
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean result = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, selection.getIdHr());
            preparedStatement.setDate(2, selection.getSelectionDate());
            preparedStatement.setString(3, selection.getStatus());
            preparedStatement.setInt(4, selection.getIdSelection());

            result = preparedStatement.execute();
            logger.info("Updating was successful!");
        } catch (SQLException e) {
            logger.error(SQL_DAO_EXCEPTION, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(SQL_CLOSE_CONNECTION_EXCEPTION, e);
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
        logger.info("Deleting selection...");
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean result = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, selection.getIdSelection());
            result = preparedStatement.execute();
            logger.info("Deleting was successful!");
        } catch (SQLException e) {
            logger.error(SQL_DAO_EXCEPTION, e);
        } finally {
            try {
                logger.info("Closing connection...");
                connection.close();
            } catch (SQLException e) {
                logger.error(SQL_CLOSE_CONNECTION_EXCEPTION, e);
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
        logger.info("New selection info inserting...");
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean result = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, selection.getIdCandidate());
            preparedStatement.setInt(2, selection.getIdVacancy());
            preparedStatement.setString(3, selection.getStatus());
            preparedStatement.setDate(4, selection.getRegistrationDate());
            result = preparedStatement.execute();
            logger.info("New selection was created successful!");
        } catch (SQLException e) {
            logger.error(SQL_DAO_EXCEPTION, e);
        } finally {
            try {
                logger.info("Closing connection...");
                connection.close();
            } catch (SQLException e) {
                logger.error(SQL_CLOSE_CONNECTION_EXCEPTION, e);
            }
        }
        return result;
    }

    public List<Selection> showSelectionsByDate(String date, boolean isRegistration) {
        logger.info("Searching selections by date: " + date);
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
            logger.error(SQL_DAO_EXCEPTION, e);
        } finally {
            try {
                logger.info("Closing connection...");
                connection.close();
            } catch (SQLException e) {
                logger.error(SQL_CLOSE_CONNECTION_EXCEPTION, e);
            }
        }
        logger.info(selections.size() + " selections was found!");
        return  selections;
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
