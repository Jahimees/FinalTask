package by.epam.ft.connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Connection pool
 * Realized by pattern Singleton
 */
public class ConnectionPool {


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
        } catch (NamingException | SQLException e) {
            return null;
        }
        return c;
    }
}
