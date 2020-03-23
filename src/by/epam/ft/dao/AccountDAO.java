package by.epam.ft.dao;

import by.epam.ft.connection.ConnectionPool;
import by.epam.ft.entity.Account;
import by.epam.ft.entity.Candidate;
import by.epam.ft.entity.Hr;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.epam.ft.constant.LogConstant.SQL_CLOSE_CONNECTION_EXCEPTION;
import static by.epam.ft.constant.LogConstant.SQL_DAO_EXCEPTION;
import static by.epam.ft.constant.PreparedConstant.*;

/**
 * manages data in the Account table
 */
public class AccountDAO implements DAO<Account> {
    private static final Logger logger = Logger.getLogger(AccountDAO.class);

    /**
     * @see DAO
     * @param id
     * @return Account object
     */
    @Override
    public Account showById(int id) {
        logger.info("Showing account by id: " + id);
        Connection connection = ConnectionPool.getInstance().getConnection();
        Account account = new Account();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_ACCOUNT);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                account.setIdAccount(rs.getInt(1));
                account.setLogin(rs.getString(2));
                account.setPassword(rs.getString(3));
                account.setName(rs.getString(4));
                account.setSurname(rs.getString(5));
                account.setBirthday(rs.getDate(6));
                account.setEmail(rs.getString(7));
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
        return account;
    }

    /**
     * show id of account by login
     * @param login
     * @return Account id
     */
    public int showIdAccountByLogin(String login) {
        logger.info("Showing account by login: " + login);
        Connection connection = ConnectionPool.getInstance().getConnection();
        int idAccount = 0;
        try {
            PreparedStatement statementForId = connection.prepareStatement(GET_ID_ACCOUNT_BY_LOGIN);
            statementForId.setString(1, login);
            ResultSet rsInner = statementForId.executeQuery();

            if (rsInner.next()) {
                idAccount = rsInner.getInt(1);
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
        return idAccount;
    }

    /**
     * Shows encrypted password by username
     * @param login
     * @return encrypted password
     */
    public String showPasswordByLogin(String login){
        logger.info("Showing password by login...");
        String passwordFromDB ="";
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_PASSWORD_BY_LOGIN);
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                passwordFromDB = rs.getString(1);
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
        return passwordFromDB;
    }

    /**
     * Show Account by id candidate or id HR
     * @param id
     * @param isHr
     * @return Account object
     */
    public Account showByIdUser(int id, boolean isHr) {
        logger.info("Showing account by id user: " + id);
        Connection connection = ConnectionPool.getInstance().getConnection();
        Account account = new Account();
            try {
                PreparedStatement statement = null;
                if (id!=0) {
                    if (isHr) {
                        statement = connection.prepareStatement(GET_ACCOUNT_BY_HR_ID);
                    } else {
                        statement = connection.prepareStatement(GET_ACCOUNT_BY_CANDIDATE_ID);
                    }
                    statement.setInt(1, id);
                    ResultSet rs = statement.executeQuery();
                    if (rs.next()) {
                        account.setIdAccount(rs.getInt(1));
                        account.setPassword(rs.getString(3));
                        account.setName(rs.getString(4));
                        account.setSurname(rs.getString(5));
                        account.setEmail(rs.getString(7));
                        logger.info("Account found!");
                        return account;
                    }
                }
            } catch (SQLException e) {
                logger.error(SQL_DAO_EXCEPTION, e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error(SQL_CLOSE_CONNECTION_EXCEPTION, e);
                }
            }
        logger.info("Account not found!");
        return null;
    }

    /**
     * Changes password in database
     * @param idAccount
     * @param password
     * @return true - if successful, otherwise - false;
     */
    public boolean changePassword(int idAccount, String password) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean result = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_PASSWORD);
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, idAccount);
            result = preparedStatement.execute();
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
        return result;
    }

    public List<Account> showByNameAndSurname(String name, String surname) {
        logger.info("Searching accounts by name and surname...");
        Connection connection = ConnectionPool.getInstance().getConnection();
        List<Account> accounts = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_NAMES);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Account account = new Account();
                account.setIdAccount(resultSet.getInt("idAccount"));
                account.setLogin(resultSet.getString("login"));
                account.setName(resultSet.getString("name"));
                account.setSurname(resultSet.getString("surname"));
                account.setBirthday(resultSet.getDate("birthday"));
                account.setEmail(resultSet.getString("email"));
                accounts.add(account);
            }
        } catch (SQLException e) {
            logger.error(SQL_DAO_EXCEPTION, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(SQL_CLOSE_CONNECTION_EXCEPTION, e);
            }
        }
        logger.info(accounts.size() + " accounts found!");
        return accounts;
    }


    /**
     * No implementation needed
     * @deprecated
     * @return
     */
    @Override
    public List<Account> showAll() {
        return null;
    }

    /**
     * No implementation needed
     * @deprecated
     * @return
     */
    @Override
    public boolean updateInfo(Account account, String query) {
        return false;
    }

    /**
     * No implementation needed
     * @deprecated
     * @return
     */
    @Override
    public boolean deleteInfo(Account account, String query) {
        return false;
    }

    /**
     * Create new account in database
     * @return
     */
    @Override
    public boolean insertInfo(Account account, String query) {
        logger.info("Creating new account in database...");
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean result = false;
        try {
            String login = account.getLogin();
            String password = account.getPassword();
            String name = account.getName();
            String surname = account.getSurname();
            Date birthday = account.getBirthday();
            String email = account.getEmail();

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, name);
            statement.setString(4, surname);
            statement.setDate(5, birthday);
            statement.setString(6, email);
            statement.execute();

            statement = connection.prepareStatement(GET_ID_ACCOUNT_BY_LOGIN);
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int idAccount = rs.getInt(1);
                Candidate candidate = new Candidate();
                candidate.setIdAccount(idAccount);
                statement = connection.prepareStatement(INSERT_INTO_CANDIDATE);
                statement.setInt(1, idAccount);
                statement.execute();
            }
            result = true;
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

}
