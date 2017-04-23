package com.time_task_manager.database.oracle;

import com.time_task_manager.database.UserDBAction;
import com.time_task_manager.model.UserData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by diman on 23.04.2017.
 */
class OracleUserSQLStatement {
    public static String addUser = "insert into users values (seq_users.nextval, ?, ?)";
    public static String deleteUser = "delete from users u where u.user_name = ?";
    public static String changePassword = "update users u set u.user_password = ? where u.user_name = ?";
    public static String getUserData = "select u.user_id, u.user_name, u.user_password from users u where u.user_name = ?";
    public static String getAllUsersData = "select u.user_id, u.user_name, u.user_password from users u";
    public static String getMaxUserID = "select max(user_id) from users";
}

public class OracleUserDBData implements UserDBAction {

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
                    psAddUser = getDbConnection().prepareStatement(OracleUserSQLStatement.addUser);
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
            System.out.println(OracleDBMessage.NO_DB_CONNECTION);
        }

        return result;
    }

    public boolean deleteUser(String userName) {
        boolean result = false;
        if (getDbConnection() != null) {
            if (psDeleteUser == null) {
                try {
                    psDeleteUser = getDbConnection().prepareStatement(OracleUserSQLStatement.deleteUser);
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
            System.out.println(OracleDBMessage.NO_DB_CONNECTION);
        }

        return result;
    }

    public boolean changePassword(String userName, String userPassword) {
        boolean result = false;
        if (getDbConnection() != null) {
            if (psChangePassword == null) {
                try {
                    psChangePassword = getDbConnection().prepareStatement(OracleUserSQLStatement.changePassword);
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
            System.out.println(OracleDBMessage.NO_DB_CONNECTION);
        }

        return result;
    }

    public UserData getUserData(String userName) {
        UserData userData = null;
        if (getDbConnection() != null) {
            if (psGetUserData == null) {
                try {
                    psGetUserData = getDbConnection().prepareStatement(OracleUserSQLStatement.getUserData);
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
                    System.out.println(OracleDBMessage.NO_RESULT);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println(OracleDBMessage.NO_DB_CONNECTION);
        }

        return userData;
    }

    public List<UserData> getAllUsersData() {
        List<UserData> listUsers = null;
        if (getDbConnection() != null) {
            if (psAllUsersData == null) {
                try {
                    psAllUsersData = getDbConnection().prepareStatement(OracleUserSQLStatement.getAllUsersData);
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
                    System.out.println(OracleDBMessage.NO_RESULT);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println(OracleDBMessage.NO_DB_CONNECTION);
        }

        return listUsers;
    }

}
