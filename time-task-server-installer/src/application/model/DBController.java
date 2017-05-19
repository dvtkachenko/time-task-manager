package application.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Locale;

/**
 * Created by diman on 26.04.2017.
 */

// before you run your project you should add "-Duser.language=en -Duser.region=us"
// string ... use menu "run/edit configurations" and parametr "VM options"
// or use in your code before connection to Oracle Locale.setDefault(Locale.ENGLISH)

public class DBController {

    private static DBController instance = new DBController();

    private DBController(){};

    public static DBController getInstance() {
        return instance;
    }

    private Connection openConnection(DBConnectionInfo connectionInfo) throws Exception {
        Class.forName(connectionInfo.getDbDriver());
        return DriverManager.getConnection(connectionInfo.getServerURL(), connectionInfo.getUserName(),connectionInfo.getUserPassword());
    }

    public void doCreationScript(Connection conn, List<String> script) {

        Statement currentStatement = null;
        for (String stringSQL : script) {

            try {
                // Execute statement
                currentStatement = conn.createStatement();
                currentStatement.execute(stringSQL);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Release resources
                if (currentStatement != null) {
                    try {
                        currentStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                currentStatement = null;
            }
        }
    }
    public static void main(String[] args) {

        DBController dbController = new DBController();

        PostgresqlDBInfo pSQL = new PostgresqlDBInfo();
        DBConnectionInfo connectionInfo = new DBConnectionInfo();

        CreationDBScript script = pSQL.getDBCreationScript();

//        System.out.println(script.getUser());
//        System.out.println(script.getDatabase());
//        System.out.println(script.getTables());

        connectionInfo.setDbDriver(pSQL.getDbDriver());
        connectionInfo.setServerURL(pSQL.getDbConnectionPrefix() + "//178.62.247.230:5432/postgres");
        connectionInfo.setUserName("timetaskmanager");
        connectionInfo.setUserPassword("12345");

        Locale defaultLocale = Locale.getDefault();

        try (Connection dbConnection = dbController.openConnection(connectionInfo)) {
            // for correct connection to Oracle database
            Locale.setDefault(Locale.ENGLISH);
            dbController.doCreationScript(dbConnection, script.getDatabase());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Locale.setDefault(defaultLocale);
        }

        connectionInfo.setServerURL(pSQL.getDbConnectionPrefix() + "//178.62.247.230:5432/timetaskmanager23");

        try (Connection dbConnection = dbController.openConnection(connectionInfo)) {
            // for correct connection to Oracle database
            Locale.setDefault(Locale.ENGLISH);
            dbController.doCreationScript(dbConnection, script.getTables());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Locale.setDefault(defaultLocale);
        }

    }

}
