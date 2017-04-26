package database;

import database.oracle.OracleDBConnectionInfo;
import database.oracle.OracleTaskDBData;
import database.oracle.OracleUserDBData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

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

        Class.forName(dbci.getDbDriver());

        return DriverManager.getConnection(dbci.getDbConnection(),
                dbci.getDbUser(),
                dbci.getDbPassword());

    }

    public static boolean createDBSession() {

        if (isSessionCreated()) return true;

        try {

            // for correct connection to Oracle database
            Locale.setDefault(Locale.ENGLISH);

            dbUserTaskConnection = openDBConnection(new OracleDBConnectionInfo());

            // ??? i need to find out about this line code ???
            Locale.setDefault(defaultLocale);

            users = new OracleUserDBData();
            tasks = new OracleTaskDBData();

            users.setDbConnection(dbUserTaskConnection);
            tasks.setDbConnection(dbUserTaskConnection);

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
