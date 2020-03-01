package by.epam.ft.dao;

import by.epam.ft.connection.ConnectionPool;
import by.epam.ft.entity.Vacancy;
import org.apache.log4j.Logger;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static by.epam.ft.constant.AttributeAndParameterConstant.*;
import static by.epam.ft.constant.LogConstant.*;
import static by.epam.ft.constant.PreparedConstant.*;

/**
 * manages data in the Vacancy table
 */
public class VacancyDAO implements DAO<Vacancy> {
    private static final Logger logger = Logger.getLogger(AccountDAO.class);

    public Vacancy showById(int id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(GET_VACANCY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Vacancy vacancy = new Vacancy();
                vacancy.setIdVacancy(resultSet.getInt(ID_VACANCY));
                vacancy.setDescription(resultSet.getString(DESCRIPTION));
                vacancy.setName(resultSet.getString(NAME));

                return vacancy;
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

        return null;
    }

    public List<Vacancy> showByName(String name) {
        List<Vacancy> vacancies = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(GET_VACANCIES_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Vacancy vacancy = new Vacancy();
                vacancy.setName(resultSet.getString(NAME));
                vacancy.setIdVacancy(resultSet.getInt(ID_VACANCY));
                vacancy.setDescription(resultSet.getString(DESCRIPTION));

                vacancies.add(vacancy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vacancies;
    }

    public LinkedHashMap<String, Integer> takePopularVacancies() {
        LinkedHashMap<String, Integer> topList = new LinkedHashMap<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_VACANCIES_WITH_COUNT);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                topList.put(resultSet.getString("name"), resultSet.getInt(COUNT_STAR));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return topList;
    }

    /**
     * No implementation needed
     * @deprecated
     * @return
     */
    @Override
    public Vacancy showById(int id, String query) {
        return null;
    }

    /**
     * @see DAO
     * @return List<Vacancy>
     */
    @Override
    public List<Vacancy> showAll() {
        List<Vacancy> result = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_VACANCIES_WITH_COUNT);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Vacancy vacancy = new Vacancy();
                vacancy.setIdVacancy(resultSet.getInt(ID_VACANCY));
                vacancy.setName(resultSet.getString(NAME));
                vacancy.setDescription(resultSet.getString(DESCRIPTION));
                vacancy.setCandidateCount(resultSet.getInt(COUNT_STAR));
                result.add(vacancy);
            }
        } catch (SQLException e) {
            logger.error(e + SQL_DAO_EXCEPTION);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e + SQL_CLOSE_CONNECTION_EXCEPTION);
            }
        }
        return result;
    }

    /**
     * No implementation needed
     * @deprecated
     * @return
     */
    @Override
    public boolean updateInfo(Vacancy vacancy, String query) {
        return false;
    }

    /**
     * Delete chosen vacancy from database
     * @param vacancy
     * @param query
     * @return
     */
    public boolean deleteInfo(Vacancy vacancy, String query) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean result = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, vacancy.getIdVacancy());
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
     * Create new Vacancy in database
     * @param vacancy
     * @param query
     * @return true - if successful, false - otherwise
     */
    public boolean insertInfo(Vacancy vacancy, String query) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean result = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, vacancy.getName());
            preparedStatement.setString(2, vacancy.getDescription());
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
