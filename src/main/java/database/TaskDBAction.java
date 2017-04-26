package database;

import entity.TaskData;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by diman on 21.04.2017.
 */

public interface TaskDBAction {
    Connection getDbConnection();
    void setDbConnection(Connection dbConnection);
    boolean addUserTask(TaskData taskData)  throws SQLException;
    boolean deleteUserTask(TaskData taskData) throws SQLException ;
    boolean deleteUserAllTasks(int userID) throws SQLException ;
    boolean updateUserTask(TaskData taskData) throws SQLException ;
    TaskData getUserTask(TaskData taskData) throws SQLException ;
    List<TaskData> getUserAllTasks(int userID) throws SQLException ;
    List<TaskData> getUserAllTasksAsTree(int userID, int nodeID) throws SQLException;
}