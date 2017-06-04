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
                throw new Exception(ERROR_DB_SCRIPT + "\n" + e.getMessage());

            } finally {
                // Release resources
                if (currentStatement != null) {
                    try {
                        currentStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        throw new Exception(ERROR_DB_SCRIPT + "\n" + e.getMessage());
                    }
                }
            }
        currentStatement = null;
    }

}
