package by.epam.ft.dao;

import by.epam.ft.connection.ConnectionPool;
import by.epam.ft.entity.Hr;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.ft.constant.AttributeAndParameterConstant.ID_HR;
import static by.epam.ft.constant.LogConstant.*;
import static by.epam.ft.constant.PreparedConstant.GET_HR_BY_ID_ACCOUNT;

/**
 * manages data in the HR table
 */
public class HrDAO implements DAO<Hr>, UserDAO {
    private static final Logger logger = Logger.getLogger(AccountDAO.class);

    /**
     * No implementation needed
     * @deprecated
     * @return
     */
    @Override
    public Hr showById(int id, String query) {
        return null;
    }

    /**
     * Shows HR info by account id
     * @param id
     * @return
     */
    @Override
    public Hr showByAccountId(int id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_HR_BY_ID_ACCOUNT);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Hr hr = new Hr();
                hr.setIdAccount(id);
                hr.setIdHr(resultSet.getInt(ID_HR));
                return hr;
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
    public List<Hr> showAll() {
        return null;
    }

    /**
     * No implementation needed
     * @deprecated
     * @return
     */
    @Override
    public boolean updateInfo(Hr hr, String query) {
        return false;
    }

    /**
     * No implementation needed
     * @deprecated
     * @return
     */
    @Override
    public boolean deleteInfo(Hr hr, String query) {
        return false;
    }

    /**
     * No implementation needed
     * @deprecated
     * @return
     */
    @Override
    public boolean insertInfo(Hr hr, String query) {
        return false;
    }


}
