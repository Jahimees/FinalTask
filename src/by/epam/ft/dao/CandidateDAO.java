package by.epam.ft.dao;

import by.epam.ft.connection.ConnectionPool;
import by.epam.ft.constant.PreparedConstant;
import by.epam.ft.entity.Candidate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.ft.constant.AttributeAndParameterConstant.ID_ACCOUNT;
import static by.epam.ft.constant.AttributeAndParameterConstant.ID_CANDIDATE;

/**
 * manages data in the Candidate table
 */
public class CandidateDAO implements DAO<Candidate>, UserDAO {


    /**
     * Show info about candidate by candidate id
     * @return
     */
    @Override
    public Candidate showById(int id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        Candidate candidate = new Candidate();
        try {
            statement = connection.prepareStatement(PreparedConstant.GET_CANDIDATE_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                setCandidateFields(resultSet, candidate);
                return candidate;
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
                setCandidateFields(rs, candidate);
                return candidate;
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

    public List<Candidate> showCandidatesByVacancyId(int idVacancy) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(PreparedConstant.GET_CANDIDATES_IDS_BY_VACANCY_WITH_WAITING_STATUS);
            statement.setInt(1, idVacancy);
            ResultSet rs = statement.executeQuery();
            List<Integer> candidatesIds = new ArrayList<>();
            while (rs.next()) {
                candidatesIds.add(rs.getInt("idCandidate"));
            }

            List<Candidate> candidateList = new ArrayList<>();
            for (Integer candidateId: candidatesIds) {
                candidateList.add(showById(candidateId));
            }
            return candidateList;
        } catch (SQLException e) {
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
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

    public void setCandidateFields(ResultSet resultSet, Candidate targetCandidate) throws SQLException {
        targetCandidate.setIdAccount(resultSet.getInt(ID_ACCOUNT));
        targetCandidate.setIdCandidate(resultSet.getInt(ID_CANDIDATE));
    }
}
