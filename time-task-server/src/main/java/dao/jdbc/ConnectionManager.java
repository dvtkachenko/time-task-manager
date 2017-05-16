package dao.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import application.ApplicationContext;
import application.exception.PropertyNotFoundException;
import application.exception.InternalServerErrorException;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;

public class ConnectionManager {
    private static ComboPooledDataSource dataSource;

    static {
        Locale.setDefault(Locale.ENGLISH);

        try {
            System.out.println("initializing connection manager...");

            String driverClass = ApplicationContext.getProperty("driverClass");
            String jdbcUrl = ApplicationContext.getProperty("jdbcUrl");
            String user = ApplicationContext.getProperty("user");
            String password = ApplicationContext.getProperty("password");

            dataSource = new ComboPooledDataSource();
            // db
            dataSource.setDriverClass(driverClass);
            dataSource.setJdbcUrl(jdbcUrl);
            dataSource.setUser(user);
            dataSource.setPassword(password);
            // connection pool
            dataSource.setMinPoolSize(10);
            dataSource.setAcquireIncrement(10);
            dataSource.setMaxPoolSize(100);
//            dataSource.setMaxConnectionAge(10*60*1000);
//            dataSource.setMaxIdleTime(1*60*1000);
            // statement pool
            dataSource.setMaxStatements(180);
            dataSource.setMaxStatementsPerConnection(10);

            System.out.println("connection manager initialized");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (PropertyNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException, InternalServerErrorException {
        if (dataSource != null)
            return dataSource.getConnection();
        else
            throw new InternalServerErrorException("ConnectionManager not initialized properly");
    }

}
