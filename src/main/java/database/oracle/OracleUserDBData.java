package database.oracle;

import database.DBMessage;
import database.UserDBAction;
import entity.UserData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by diman on 23.04.2017.
 */

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

    // ! for correct work field "user_name" in database needs constraint !
    public boolean addUser(UserData userData) throws SQLException {
        boolean result = false;
        if (getDbConnection() == null) throw new SQLException(DBMessage.NO_DB_CONNECTION);
        if (psAddUser == null) {
            psAddUser = getDbConnection().prepareStatement(OracleSQLStatement.Users.addUser);
        }
        psAddUser.setString(1, userData.getUserName());
        psAddUser.setString(2, userData.getUserPassword());

        if (psAddUser.executeUpdate() > 0) result = true;

        return result;
    }

    public boolean deleteUser(UserData userData) throws SQLException {
        boolean result = false;
        if (getDbConnection() == null) throw new SQLException(DBMessage.NO_DB_CONNECTION);
        if (psDeleteUser == null) {
            psDeleteUser = getDbConnection().prepareStatement(OracleSQLStatement.Users.deleteUser);
        }
        psDeleteUser.setString(1, userData.getUserName());
        if (psDeleteUser.executeUpdate() > 0) result = true;

        return result;
    }

    public boolean changePassword(UserData userData) throws SQLException {
        boolean result = false;
        if (getDbConnection() == null) throw new SQLException(DBMessage.NO_DB_CONNECTION);
        if (psChangePassword == null) {
            psChangePassword = getDbConnection().prepareStatement(OracleSQLStatement.Users.changePassword);
        }
        psChangePassword.setString(1, userData.getUserPassword());
        psChangePassword.setString(2, userData.getUserName());
        if (psChangePassword.executeUpdate() > 0) result = true;

        return result;
    }

    public UserData getUserData(UserData userData) throws SQLException {
        UserData userNewData = null;
        if (getDbConnection() == null) throw new SQLException(DBMessage.NO_DB_CONNECTION);
        if (psGetUserData == null) {
            psGetUserData = getDbConnection().prepareStatement(OracleSQLStatement.Users.getUserData);
        }
        psGetUserData.setString(1, userData.getUserName());
        ResultSet rs = psGetUserData.executeQuery();
        // there is no checking situation when result has more then 1 row
        // ! for correct work field "user_name" in database needs constraint !
        if (rs.next()) {
            userNewData = new UserData();
            userNewData.setUserID(rs.getInt("user_id"));
            userNewData.setUserName(rs.getString("user_name"));
            userNewData.setUserPassword(rs.getString("user_password"));
        }
        //        } else {
//            System.out.println(DBMessage.NO_RESULT);
//        }

        return userNewData;
    }

    public List<UserData> getAllUsersData() throws SQLException {
        List<UserData> listUsers = null;
        if (getDbConnection() == null) throw new SQLException(DBMessage.NO_DB_CONNECTION);
        if (psAllUsersData == null) {
            psAllUsersData = getDbConnection().prepareStatement(OracleSQLStatement.Users.getAllUsersData);
        }
        ResultSet rs = psAllUsersData.executeQuery();

        if (rs.next()) {
            listUsers = new ArrayList<UserData>();
            do {
                UserData userData = new UserData();
                userData.setUserID(rs.getInt("user_id"));
                userData.setUserName(rs.getString("user_name"));
                userData.setUserPassword(rs.getString("user_password"));
                listUsers.add(userData);
            } while (rs.next());
        }

//        } else {
//            System.out.println(DBMessage.NO_RESULT);
//        }
//
        return listUsers;
    }

}
