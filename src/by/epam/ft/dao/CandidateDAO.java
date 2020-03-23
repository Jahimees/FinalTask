package by.epam.ft.dao;

import by.epam.ft.constant.PreparedConstant;
import by.epam.ft.connection.ConnectionPool;
import by.epam.ft.entity.Candidate;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static by.epam.ft.constant.AttributeAndParameterConstant.*;
import static by.epam.ft.constant.LogConstant.*;

/**
 * manages data in the Candidate table
 */
public class CandidateDAO implements DAO<Candidate>, UserDAO {

    private static final Logger logger = Logger.getLogger(AccountDAO.class);

    /**
     * Show info about candidate by candidate id
     * @return
     */
    @Override
    public Candidate showById(int id) {
        logger.info("Getting candidate by id candidate: " + id);
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        Candidate candidate = new Candidate();
        try {
            statement = connection.prepareStatement(PreparedConstant.GET_CANDIDATE_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                setCandidateFields(resultSet, candidate);
                logger.info("Candidate found!");
                return candidate;
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
        logger.info("Candidate not found!");
        return null;
    }

    /**
     * Shows candidate info by account id
     * @param accId
     * @return Candidate
     */
    @Override
    public Candidate showByAccountId(int accId) {
        logger.info("Showing Candidate by account id: " + accId);
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(PreparedConstant.GET_CANDIDATE_BY_ACCOUNT_ID);
            statement.setInt(1, accId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Candidate candidate = new Candidate();
                setCandidateFields(rs, candidate);
                logger.info("Candidate found!");
                return candidate;
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
        logger.info("Candidate not found!");
        return null;
    }

    /**
     * No implementation needed
     * @deprecated
     * @return
     */
    @Override
    public List<Candidate> showAll() {
        return null;
    }

    /**
     * No implementation needed
     * @deprecated
     * @return
     */
    @Override
    public boolean updateInfo(Candidate candidate, String query) {
        return false;
    }

    /**
     * No implementation needed
     * @deprecated
     * @return
     */
    @Override
    public boolean deleteInfo(Candidate candidate, String query) {
        return false;
    }

    /**
     * No implementation needed
     * All candidates are created automatically when creates new account
     * For connection security, this method is not implemented
     * @see AccountDAO insertInfo method
     * @deprecated
     * @return
     */
    @Override
    public boolean insertInfo(Candidate candidate, String query) {
        return false;
    }

    public void setCandidateFields(ResultSet resultSet, Candidate targetCandidate) throws SQLException {
        targetCandidate.setIdAccount(resultSet.getInt(ID_ACCOUNT));
        targetCandidate.setIdCandidate(resultSet.getInt(ID_CANDIDATE));
    }
}
