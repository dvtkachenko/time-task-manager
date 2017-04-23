package com.time_task_manager.database.oracle;

import com.time_task_manager.database.DBAction;
import com.time_task_manager.database.DBConnectionInfo;

import java.sql.*;
import java.util.Locale;

/**
 * Created by diman on 19.04.2017.
 */

// before you run your project you should add "-Duser.language=en -Duser.region=us"
// string ... use menu "run/edit configurations" and parametr "VM options"
// or use in your code before connection to Oracle Locale.setDefault(Locale.ENGLISH)


public class OracleDBAction implements DBAction {

    private Locale defaultLocale = Locale.getDefault();

    public Connection dbUserTaskConnection = null;

    public OracleUserDBData userDBData = new OracleUserDBData();
//    private OracleTaskDBData taskDBData = new OracleTaskDBData;

    @Override
    public boolean openDBConnection (DBConnectionInfo dbci) {

        if (dbUserTaskConnection != null) return true;

        try {
            Class.forName(dbci.DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }

        try {
            // for correct connection to Oracle database
            Locale.setDefault(Locale.ENGLISH);
//        System.out.println(Locale.getDefault().getDisplayCountry() + Locale.getDefault().getDisplayLanguage());

            dbUserTaskConnection = DriverManager.getConnection(dbci.DB_CONNECTION,
                    dbci.DB_USER,
                    dbci.DB_PASSWORD);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {

            // ??? i need to find out about this line code ???
            Locale.setDefault(defaultLocale);
        }

    }

    public void closeDBConnection () {
        try {
            dbUserTaskConnection.close();
            dbUserTaskConnection = null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Locale.setDefault(defaultLocale);
        }
    }

    public static void main(String[] args) {

        // I have to think about how I can to refactor
        // this silly part of code
        DBConnectionInfo dbci = new DBConnectionInfo();
        dbci.DB_DRIVER = OracleDBConnectionInfo.DB_DRIVER;
        dbci.DB_CONNECTION = OracleDBConnectionInfo.DB_CONNECTION;
        dbci.DB_USER = OracleDBConnectionInfo.DB_USER;
        dbci.DB_PASSWORD = OracleDBConnectionInfo.DB_PASSWORD;

        OracleDBAction oracleDB = new OracleDBAction();
        oracleDB.openDBConnection(dbci);

        oracleDB.userDBData.setDbConnection(oracleDB.dbUserTaskConnection);
//        if (oracleDB.userDBData.addUser("andrey3","12345")) System.out.println("user was added");
//        if (oracleDB.userDBData.addUser("andrey4","12345")) System.out.println("user was added");
//        if (oracleDB.userDBData.addUser("anton","12345")) System.out.println("user was added");
//        if (oracleDB.userDBData.addUser("gleb","12345")) System.out.println("user was added");
//        if (oracleDB.userDBData.addUser("oksana","12345")) System.out.println("user was added");
//        if (oracleDB.userDBData.addUser("tolik","12345")) System.out.println("user was added");

//        if (oracleDB.userDBData.deleteUser("andrey3")) System.out.println("user was deleted");
//        if (oracleDB.userDBData.deleteUser("andrey4")) System.out.println("user was deleted");
//        if (oracleDB.userDBData.deleteUser("anton")) System.out.println("user was deleted");
//        if (oracleDB.userDBData.deleteUser("gleb")) System.out.println("user was deleted");
//        if (oracleDB.userDBData.deleteUser("oksana")) System.out.println("user was deleted");
//        if (oracleDB.userDBData.deleteUser("tolik")) System.out.println("user was deleted");

//        if (oracleDB.userDBData.changePassword("andrey3","qwef")) System.out.println("password was changed");
//        if (oracleDB.userDBData.changePassword("anton","asgasdvz")) System.out.println("password was changed");
//        if (oracleDB.userDBData.changePassword("gleb","qq23rwasf")) System.out.println("password was changed");
//        if (oracleDB.userDBData.changePassword("oksana","1wvg4g")) System.out.println("password was changed");
//        if (oracleDB.userDBData.changePassword("tolik","54321")) System.out.println("password was changed");

//        System.out.println(oracleDB.userDBData.getUserData("andrey3"));
//        System.out.println(oracleDB.userDBData.getUserData("anton"));
//        System.out.println(oracleDB.userDBData.getUserData("gleb"));
//        System.out.println(oracleDB.userDBData.getUserData("oksana"));
//        System.out.println(oracleDB.userDBData.getUserData("tolik"));

        System.out.println(oracleDB.userDBData.getAllUsersData());

        oracleDB.closeDBConnection();
    }
}
