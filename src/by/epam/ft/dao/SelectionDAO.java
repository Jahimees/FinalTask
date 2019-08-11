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
    private static final Logger logger = Logger.getLogger(AccountDAO.class);

    /**
     * @see DAO
     * @param id
     * @param query
     * @return
     */
    @Override
    public Selection showById(int id, String query) {
        return null;
    }

    /**
     * Shows all vacancies which candidate register for
     * @param idCandidate
     * @param query
     * @return
     */
    public List<Selection> showSelections(int idCandidate, String query) {
        List<Selection> selections = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCandidate);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Selection selection = new Selection();
                selection.setIdSelection(rs.getInt(ID_SELECTION));
                selection.setIdHr(rs.getInt(ID_HR));
                selection.setIdCandidate(rs.getInt(ID_CANDIDATE));
                selection.setSelectionDate(rs.getDate(SELECTION_DATE));
                selection.setIdVacancy(rs.getInt(ID_VACANCY));
                selection.setStatus(rs.getString(STATUS));
                selections.add(selection);
            }
        } catch (SQLException e) {
            logger.error(e + SQL_DAO_EXCEPTION);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e + SQL_CLOSE_CONNECTION_EXCEPTION);
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
                selection.setIdCandidate(rs.getInt(ID_CANDIDATE));
                selection.setIdHr(rs.getInt(ID_HR));
                selection.setIdSelection(rs.getInt(ID_SELECTION));
                selection.setSelectionDate(rs.getDate(SELECTION_DATE));
                selection.setStatus(rs.getString(STATUS));
                selection.setIdVacancy(rs.getInt(ID_VACANCY));
                selections.add(selection);
            }
        } catch (SQLException e) {
            logger.error(e + SQL_DAO_EXCEPTION);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e + SQL_CLOSE_CONNECTION_EXCEPTION);
            }
        }
        return selections;
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
            logger.error(e + SQL_DAO_EXCEPTION);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e + SQL_CLOSE_CONNECTION_EXCEPTION);
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
            logger.error(e + SQL_DAO_EXCEPTION);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e + SQL_CLOSE_CONNECTION_EXCEPTION);
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
                selection.setIdSelection(resultSet.getInt(ID_SELECTION));
                selection.setIdCandidate(resultSet.getInt(ID_CANDIDATE));
                selection.setIdHr(resultSet.getInt(ID_HR));
                selection.setStatus(resultSet.getString(STATUS));
                selection.setSelectionDate(resultSet.getDate(SELECTION_DATE));
                selection.setIdVacancy(resultSet.getInt(ID_VACANCY));
                resultList.add(selection);
            }
        } catch (SQLException e) {
            logger.error(e + SQL_DAO_EXCEPTION);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e + SQL_CLOSE_CONNECTION_EXCEPTION);
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
            logger.error(e + SQL_DAO_EXCEPTION);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e + SQL_CLOSE_CONNECTION_EXCEPTION);
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
            logger.error(e + SQL_DAO_EXCEPTION);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e + SQL_CLOSE_CONNECTION_EXCEPTION);
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
            result = preparedStatement.execute();
        } catch (SQLException e) {
            logger.error(e + SQL_DAO_EXCEPTION);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e + SQL_CLOSE_CONNECTION_EXCEPTION);
            }
        }
        return result;
    }

}
