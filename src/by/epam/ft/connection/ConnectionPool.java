package by.epam.ft.connection;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static by.epam.ft.constant.LogConstant.CONNECTION_POOL_CREATED;
import static by.epam.ft.constant.LogConstant.CONNECTION_POOL_PROBLEMS;

/**
 * Connection pool
 * Realized by pattern Singleton
 */
public class ConnectionPool {
    private static final Logger logger = Logger.getLogger(ConnectionPool.class);

    /**
     * private constructor
     */
    private ConnectionPool(){
    }

    /**
     * Single object of class Connection Pool
     */
    private static ConnectionPool instance = null;

    /**
     * Singleton realization
     */
    public static ConnectionPool getInstance(){

        if (instance==null) {
            instance = new ConnectionPool();
            logger.info(CONNECTION_POOL_CREATED);
        }
        return instance;
    }

    /**
     * Open connection to database
     * @return connection
     */
    public Connection getConnection(){
        Context ctx;
        Connection c = null;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mainPool");
            c = ds.getConnection();
        } catch (NamingException e) {
            logger.error(CONNECTION_POOL_PROBLEMS, e);
        } catch (SQLException e) {
            logger.error(CONNECTION_POOL_PROBLEMS, e);
        }
        return c;
    }

}
