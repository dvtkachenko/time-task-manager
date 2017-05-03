package database;

import database.oracle.OracleDBConnectionInfo;
import database.oracle.OracleTaskDBData;
import database.oracle.OracleUserDBData;
import database.postgresql.PostgresqlDBConnectionInfo;
import database.postgresql.PostgresqlTaskDBData;
import database.postgresql.PostgresqlUserDBData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by diman on 26.04.2017.
 */

// before you run your project you should add "-Duser.language=en -Duser.region=us"
// string ... use menu "run/edit configurations" and parametr "VM options"
// or use in your code before connection to Oracle Locale.setDefault(Locale.ENGLISH)

public class DBManager {

    public static UserDBAction users = null;
    public static TaskDBAction tasks = null;

    private static boolean sessionCreated = false;
    private static Connection dbUserTaskConnection = null;
    private static final Locale defaultLocale = Locale.getDefault();

    private DBManager(){};

    public static boolean isSessionCreated() {
        return sessionCreated;
    }

    private static Connection openDBConnection(DBConnectionInfo dbci) throws Exception {

        Connection conn = null;

        // this part of code is krivojopo ... but a will fix it later
        if (dbci instanceof OracleDBConnectionInfo) {
            Class.forName(dbci.getDbDriver());

            conn = DriverManager.getConnection(dbci.getDbConnection(),
                    dbci.getDbUser(),
                    dbci.getDbPassword());
        }

        if (dbci instanceof PostgresqlDBConnectionInfo) {
            Class.forName(dbci.getDbDriver());

            Properties props = new Properties();
            props.setProperty("user",dbci.getDbUser());
            props.setProperty("password",dbci.getDbPassword());
            props.setProperty("ssl","true");

            conn = DriverManager.getConnection(dbci.getDbConnection(),props);
        }

        return conn;
    }

    public static boolean createDBSession() {

        if (isSessionCreated()) return true;

        try {

            // for correct connection to Oracle database
            Locale.setDefault(Locale.ENGLISH);

//            dbUserTaskConnection = openDBConnection(new OracleDBConnectionInfo());
            dbUserTaskConnection = openDBConnection(new PostgresqlDBConnectionInfo());

            // ??? i need to find out about this line code ???
            Locale.setDefault(defaultLocale);

//            users = new OracleUserDBData();
//            tasks = new OracleTaskDBData();

            users = new PostgresqlUserDBData();
//            tasks = new PostgresqlTaskDBData();

            users.setDbConnection(dbUserTaskConnection);
//            tasks.setDbConnection(dbUserTaskConnection);

            sessionCreated = true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean closeDBSession() {
        try {
            dbUserTaskConnection.close();
            dbUserTaskConnection = null;
            users = null;
            tasks = null;
            sessionCreated = false;
            // ??? i need to find out about this line code ???
            Locale.setDefault(defaultLocale);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
