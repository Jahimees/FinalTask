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
     * No implementation needed
     * @deprecated
     * @return
     */
    @Override
    public Candidate showById(int id, String query) {
        return null;
    }

    /**
     * Shows candidate info by account id
     * @param accId
     * @return Candidate
     */
    @Override
    public Candidate showByAccountId(int accId) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(PreparedConstant.GET_CANDIDATE_BY_ACCOUNT_ID);
            statement.setInt(1, accId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Candidate candidate = new Candidate();
                candidate.setIdCandidate(rs.getInt(ID_CANDIDATE));
                candidate.setIdAccount(rs.getInt(ID_ACCOUNT));

                return candidate;
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


}
