package database.oracle;

import database.DBAction;
import database.DBConnectionInfo;

import java.sql.*;

/**
 * Created by diman on 19.04.2017.
 */

// before you run your project you should add "-Duser.language=en -Duser.region=us"
// string ... use menu "run/edit configurations" and parametr "VM options"
// or use in your code before connection to Oracle Locale.setDefault(Locale.ENGLISH)

public class OracleDB implements DBAction {

    public Connection dbUserTaskConnection = null;

    public OracleUserDBData users = new OracleUserDBData();
    public OracleTaskDBData tasks = new OracleTaskDBData();

    public boolean openDBConnection(DBConnectionInfo dbci) throws Exception {

        if (dbUserTaskConnection != null) return true;

        Class.forName(dbci.getDbDriver());

        dbUserTaskConnection = DriverManager.getConnection(dbci.getDbConnection(),
                dbci.getDbUser(),
                dbci.getDbPassword());

        users.setDbConnection(dbUserTaskConnection);
        tasks.setDbConnection(dbUserTaskConnection);

        return true;
    }

    public void closeDBConnection() throws Exception {
        dbUserTaskConnection.close();
        dbUserTaskConnection = null;

        users.setDbConnection(dbUserTaskConnection);
        tasks.setDbConnection(dbUserTaskConnection);
    }

}
