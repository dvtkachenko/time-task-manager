package application;

import application.model.DBInfo;
import application.model.MySQLDBInfo;
import application.model.OracleDBInfo;
import application.model.PostgresqlDBInfo;


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

public class DBController {

    private static boolean sessionCreated = false;
    private static Connection dbConnection = null;
    private static final Locale defaultLocale = Locale.getDefault();

    private DBController(){};

    public static boolean isSessionCreated() {
        return sessionCreated;
    }

    private static Connection openDBConnection(DBInfo dbci) throws Exception {

        Connection conn = null;

        // this part of code is krivojopo ... but a will fix it later
        if (dbci instanceof OracleDBInfo) {
            Class.forName(dbci.getDbDriver());

            conn = DriverManager.getConnection(dbci.getDbConnectionPrefix());
        }

        if (dbci instanceof MySQLDBInfo) {
            Class.forName(dbci.getDbDriver());

            conn = DriverManager.getConnection(dbci.getDbConnectionPrefix());
        }

        if (dbci instanceof PostgresqlDBInfo) {
            Class.forName(dbci.getDbDriver());

            conn = DriverManager.getConnection(dbci.getDbConnectionPrefix());
        }

        return conn;
    }

    public static boolean createDBSession() {

        if (isSessionCreated()) return true;

        try {

            // for correct connection to Oracle database
            Locale.setDefault(Locale.ENGLISH);

//            dbUserTaskConnection = openDBConnection(new OracleDBInfo());
//            dbUserTaskConnection = openDBConnection(new PostgresqlDBInfo());
            dbConnection = openDBConnection(new MySQLDBInfo());

            // ??? i need to find out about this line code ???
            Locale.setDefault(defaultLocale);

            sessionCreated = true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean closeDBSession() {
        try {
            dbConnection.close();
            dbConnection = null;
            sessionCreated = false;
            // ??? i need to find out about this line code ???
            Locale.setDefault(defaultLocale);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void doCreationScript() {

    }

}
