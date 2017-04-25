package database;

import entity.UserData;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by diman on 19.04.2017.
 */
public interface UserDBAction {
    boolean addUser(UserData userData)  throws SQLException;
    boolean deleteUser(UserData userData) throws SQLException ;
    boolean changePassword(UserData userData) throws SQLException ;
    UserData getUserData(UserData userData) throws SQLException ;
    List<UserData> getAllUsersData() throws SQLException ;
}