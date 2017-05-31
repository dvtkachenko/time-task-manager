package application.controller;


import application.model.CreationDBScript;
import application.model.DBConnectionInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

import static application.controller.MainController.ERROR_DB_SCRIPT;

/**
 * Created by diman on 26.04.2017.
 *
 * This class is a database controller for this application
 * It handles database connections
 *
 */

// before you run your project you should add "-Duser.language=en -Duser.region=us"
// string ... use menu "run/edit configurations" and parametr "VM options"
// or use in your code before connection to Oracle Locale.setDefault(Locale.ENGLISH)

public class DBController {


    public Connection openConnection(DBConnectionInfo connectionInfo) throws Exception {

        Class.forName(connectionInfo.getDbDriver());
        return DriverManager.getConnection(connectionInfo.getDbFullPathConnection(), connectionInfo.getUserName(),connectionInfo.getUserPassword());
    }

    public void doCreationScript(Connection conn, String script) throws Exception {

        Statement currentStatement = null;
            try {
                // Execute statement
                currentStatement = conn.createStatement();
                currentStatement.execute(script);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new Exception(ERROR_DB_SCRIPT);

            } finally {
                // Release resources
                if (currentStatement != null) {
                    try {
                        currentStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        throw new Exception(ERROR_DB_SCRIPT);
                    }
                }
            }
        currentStatement = null;
    }

    public static void main(String[] args) {

        DBController dbController = new DBController();

        DBConnectionInfo connectionInfo = new DBConnectionInfo(DBConnectionInfo.RDBMS.PostgreSQL);

        CreationDBScript script = new CreationDBScript();
        script.readScriptFiles(connectionInfo.getScriptSuffixFileName());

//        System.out.println(script.getUser());
//        System.out.println(script.getDatabase());
//        System.out.println(script.getTables());

//        connectionInfo.setDbDriver(pSQL.getDbDriver());
        connectionInfo.setDbFullPathConnection(connectionInfo.getDbConnectionPrefix() + "//178.62.247.230:5432/postgres");
//        connectionInfo.setUserName("timetaskmanager");
//        connectionInfo.setUserPassword("12345");

        Locale defaultLocale = Locale.getDefault();

        try (Connection dbConnection = dbController.openConnection(connectionInfo)) {
            // for correct connection to Oracle database
            Locale.setDefault(Locale.ENGLISH);
            dbController.doCreationScript(dbConnection, script.getDatabaseScript());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Locale.setDefault(defaultLocale);
        }

        connectionInfo.setDbFullPathConnection(connectionInfo.getDbConnectionPrefix() + "//178.62.247.230:5432/timetaskmanager2");

        try (Connection dbConnection = dbController.openConnection(connectionInfo)) {
            // for correct connection to Oracle database
            Locale.setDefault(Locale.ENGLISH);
            dbController.doCreationScript(dbConnection, script.getTablesScript());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Locale.setDefault(defaultLocale);
        }

    }

}
