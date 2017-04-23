package com.time_task_manager.database.oracle;

import com.time_task_manager.database.DBAction;
import com.time_task_manager.database.DBConnectionInfo;
import com.time_task_manager.database.UserDBAction;
import com.time_task_manager.model.UserData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by diman on 19.04.2017.
 */

// before you run your project you should add "-Duser.language=en -Duser.region=us"
// string ... use menu "run/edit configurations" and parametr "VM options"
// or use in your code before connection to Oracle Locale.setDefault(Locale.ENGLISH)
final class DBErrorMessage {
    public final static  String NO_DB_CONNECTION = "There is no database connection ! Please connect to database !";
    public final static  String NO_RESULT = "There is no result !";
}

final class OracleDBCI {
    public final static String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public final static String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";

    public final static String DB_USER = "TIMETASKMANAGER";
//    public static final String DB_USER = "SYSTEM";
    public final static String DB_PASSWORD = "12345";
//    public static final String DB_PASSWORD = "admin1977";
//    public static final String selectTableSQL = "SELECT * FROM USERS";
}

class OracleUserDBData implements UserDBAction {

    private Connection dbConnection = null;

    private PreparedStatement psAddUser = null;
    private PreparedStatement psDeleteUser = null;
    private PreparedStatement psChangePassword = null;
    private PreparedStatement psGetUserData = null;
    private PreparedStatement psAllUsersData = null;

    public Connection getDbConnection() {
        return dbConnection;
    }

    public void setDbConnection(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public boolean addUser(String userName, String userPassword) {
        boolean result = false;
        if (getDbConnection() != null) {
            if (psAddUser == null) {
                try {
                    psAddUser = getDbConnection().prepareStatement(UsersSQLStatement.addUser);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                psAddUser.setString(1, userName);
                psAddUser.setString(2, userPassword);
                if (psAddUser.executeUpdate() > 0) result = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(DBErrorMessage.NO_DB_CONNECTION);
        }

        return result;
    }

    public boolean deleteUser(String userName) {
        boolean result = false;
        if (getDbConnection() != null) {
            if (psDeleteUser == null) {
                try {
                    psDeleteUser = getDbConnection().prepareStatement(UsersSQLStatement.deleteUser);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                psDeleteUser.setString(1, userName);
                if (psDeleteUser.executeUpdate() > 0) result = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println(DBErrorMessage.NO_DB_CONNECTION);
        }

        return result;
    }

    public boolean changePassword(String userName, String userPassword) {
        boolean result = false;
        if (getDbConnection() != null) {
            if (psChangePassword == null) {
                try {
                    psChangePassword = getDbConnection().prepareStatement(UsersSQLStatement.changePassword);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                psChangePassword.setString(1, userPassword);
                psChangePassword.setString(2, userName);
                if (psChangePassword.executeUpdate() > 0) result = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println(DBErrorMessage.NO_DB_CONNECTION);
        }

        return result;
    }

    public UserData getUserData(String userName) {
        UserData userData = null;
        if (getDbConnection() != null) {
            if (psGetUserData == null) {
                try {
                    psGetUserData = getDbConnection().prepareStatement(UsersSQLStatement.getUserData);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                psGetUserData.setString(1, userName);
                ResultSet rs = psGetUserData.executeQuery();
                // there is no checking situation when result has more then 1 row
                if (rs.next()) {
                    userData = new UserData();
                    userData.setUserID(rs.getInt(1));
                    userData.setUserName(rs.getString(2));
                    userData.setUserPassword(rs.getString(3));
                } else {
                    System.out.println(DBErrorMessage.NO_RESULT);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println(DBErrorMessage.NO_DB_CONNECTION);
        }

        return userData;
    }

    public List<UserData> getAllUsersData() {
        List<UserData> listUsers = null;
        if (getDbConnection() != null) {
            if (psAllUsersData == null) {
                try {
                    psAllUsersData = getDbConnection().prepareStatement(UsersSQLStatement.getAllUsersData);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                ResultSet rs = psAllUsersData.executeQuery();

                if (rs.next()) {
                    listUsers = new ArrayList<>();
                    do {
                        UserData userData = new UserData();
                        userData.setUserID(rs.getInt(1));
                        userData.setUserName(rs.getString(2));
                        userData.setUserPassword(rs.getString(3));
                        listUsers.add(userData);
                    } while (rs.next());
                } else {
                    System.out.println(DBErrorMessage.NO_RESULT);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println(DBErrorMessage.NO_DB_CONNECTION);
        }

        return listUsers;
    }

}

public class OracleDB implements DBAction {

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
        dbci.DB_DRIVER = OracleDBCI.DB_DRIVER;
        dbci.DB_CONNECTION = OracleDBCI.DB_CONNECTION;
        dbci.DB_USER = OracleDBCI.DB_USER;
        dbci.DB_PASSWORD = OracleDBCI.DB_PASSWORD;

        OracleDB oracleDB = new OracleDB();
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
