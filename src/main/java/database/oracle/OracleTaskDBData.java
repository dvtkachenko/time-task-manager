package database.oracle;

import database.DBMessage;
import database.TaskDBAction;
import entity.TaskData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by diman on 24.04.2017.
 */
public class OracleTaskDBData implements TaskDBAction {

    private Connection dbConnection = null;

    private PreparedStatement psAddUserTask = null;
    private PreparedStatement psDeleteUserTask = null;
    private PreparedStatement psDeleteUserAllTasks = null;
    private PreparedStatement psUpdateUserTask = null;
    private PreparedStatement psGetUserTask = null;
    private PreparedStatement psGetUserAllTasks = null;
    private PreparedStatement psGetUserAllTasksAsTree = null;

    public Connection getDbConnection() {
        return dbConnection;
    }

    public void setDbConnection(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public boolean addUserTask(TaskData taskData)  throws SQLException {
        boolean result = false;
        if (getDbConnection() == null) throw new SQLException(DBMessage.NO_DB_CONNECTION);
        if (psAddUserTask == null) {
            psAddUserTask = getDbConnection().prepareStatement(OracleSQLStatement.Tasks.addUserTask);
        }
        psAddUserTask.setInt(1, taskData.getParentTaskID());
        psAddUserTask.setInt(2, taskData.getChildTaskID());
        psAddUserTask.setInt(3, taskData.getTaskLevel());
        psAddUserTask.setInt(4, taskData.getUserID());
        psAddUserTask.setString(5, taskData.getTaskName());
        psAddUserTask.setDate(6, taskData.getPlanStartTime());
        psAddUserTask.setDate(7, taskData.getPlanEndTime());
        psAddUserTask.setInt(8, taskData.getPlanDuration());
        psAddUserTask.setDate(9, taskData.getFactStartTime());
        psAddUserTask.setDate(10, taskData.getFactEndTime());
        psAddUserTask.setInt(11, taskData.getFactDuration());
        psAddUserTask.setInt(12, taskData.getNumVersion());
        psAddUserTask.setString(13, taskData.isCompleted());

        if (psAddUserTask.executeUpdate() > 0) result = true;

        return result;
    }

    public boolean deleteUserTask(TaskData taskData) throws SQLException {
        boolean result = false;
        if (getDbConnection() == null) throw new SQLException(DBMessage.NO_DB_CONNECTION);
        if (psDeleteUserTask == null) {
            psDeleteUserTask = getDbConnection().prepareStatement(OracleSQLStatement.Tasks.deleteUserTask);
        }
        psDeleteUserTask.setInt(1, taskData.getTaskID());
        psDeleteUserTask.setInt(2, taskData.getUserID());

        if (psDeleteUserTask.executeUpdate() > 0) result = true;

        return result;
    }

    public boolean deleteUserAllTasks(int userID) throws SQLException {
        boolean result = false;
        if (getDbConnection() == null) throw new SQLException(DBMessage.NO_DB_CONNECTION);
        if (psDeleteUserAllTasks == null) {
            psDeleteUserAllTasks = getDbConnection().prepareStatement(OracleSQLStatement.Tasks.deleteUserAllTasks);
        }
        psDeleteUserAllTasks.setInt(1, userID);

        if (psDeleteUserAllTasks.executeUpdate() > 0) result = true;

        return result;
    }

    public boolean updateUserTask(TaskData taskData) throws SQLException {
        boolean result = false;
        if (getDbConnection() == null) throw new SQLException(DBMessage.NO_DB_CONNECTION);
        if (psAddUserTask == null) {
            psUpdateUserTask = getDbConnection().prepareStatement(OracleSQLStatement.Tasks.updateUserTask);
        }
        psUpdateUserTask.setInt(1, taskData.getParentTaskID());
        psUpdateUserTask.setInt(2, taskData.getChildTaskID());
        psUpdateUserTask.setInt(3, taskData.getTaskLevel());
        psUpdateUserTask.setInt(4, taskData.getUserID());
        psUpdateUserTask.setString(5, taskData.getTaskName());
        psUpdateUserTask.setDate(6, taskData.getPlanStartTime());
        psUpdateUserTask.setDate(7, taskData.getPlanEndTime());
        psUpdateUserTask.setInt(8, taskData.getPlanDuration());
        psUpdateUserTask.setDate(9, taskData.getFactStartTime());
        psUpdateUserTask.setDate(10, taskData.getFactEndTime());
        psUpdateUserTask.setInt(11, taskData.getFactDuration());
        psUpdateUserTask.setInt(12, taskData.getNumVersion());
        psUpdateUserTask.setString(13, taskData.isCompleted());

        if (psUpdateUserTask.executeUpdate() > 0) result = true;

        return result;
    }

    public TaskData getUserTask(TaskData taskData) throws SQLException {
        TaskData taskNewData = null;
        if (getDbConnection() == null) throw new SQLException(DBMessage.NO_DB_CONNECTION);
        if (psGetUserTask == null) {
            psGetUserTask = getDbConnection().prepareStatement(OracleSQLStatement.Tasks.getUserTask);
        }
        psGetUserTask.setInt(1, taskData.getTaskID());
        psGetUserTask.setInt(2, taskData.getUserID());
        ResultSet rs = psGetUserTask.executeQuery();
        // there is no checking situation when result has more then 1 row
        // ! for correct work field "user_name" in database needs constraint !
        if (rs.next()) {
            taskNewData = new TaskData();
            taskNewData.setTaskID(rs.getInt("task_id"));
            taskNewData.setParentTaskID(rs.getInt("parent_task_id"));
            taskNewData.setChildTaskID(rs.getInt("child_task_id"));
            taskNewData.setTaskLevel(rs.getInt("task_level"));
            taskNewData.setUserID(rs.getInt("user_id"));
            taskNewData.setTaskName(rs.getString("task_name"));
            taskNewData.setPlanStartTime(rs.getDate("plan_start_time"));
            taskNewData.setPlanEndTime(rs.getDate("plan_end_time"));
            taskNewData.setPlanDuration(rs.getInt("plan_duration"));
            taskNewData.setFactStartTime(rs.getDate("fact_start_time"));
            taskNewData.setFactEndTime(rs.getDate("fact_end_time"));
            taskNewData.setFactDuration(rs.getInt("fact_duration"));
            taskNewData.setNumVersion(rs.getInt("num_version"));
            taskNewData.setIsCompleted(rs.getString("is_completed"));
        }

        return taskNewData;
    }

    public List<TaskData> getUserAllTasks(int userID) throws SQLException {
        List<TaskData> listTasks = null;
        if (getDbConnection() == null) throw new SQLException(DBMessage.NO_DB_CONNECTION);
        if (psGetUserAllTasks == null) {
            psGetUserAllTasks = getDbConnection().prepareStatement(OracleSQLStatement.Tasks.getUserAllTasks);
        }

        psGetUserAllTasks.setInt(1, userID);
        ResultSet rs = psGetUserAllTasks.executeQuery();

        if (rs.next()) {
            listTasks = new ArrayList<TaskData>();
            do {
                TaskData taskData = new TaskData();
                taskData.setTaskID(rs.getInt("task_id"));
                taskData.setParentTaskID(rs.getInt("parent_task_id"));
                taskData.setChildTaskID(rs.getInt("child_task_id"));
                taskData.setTaskLevel(rs.getInt("task_level"));
                taskData.setUserID(rs.getInt("user_id"));
                taskData.setTaskName(rs.getString("task_name"));
                taskData.setPlanStartTime(rs.getDate("plan_start_time"));
                taskData.setPlanEndTime(rs.getDate("plan_end_time"));
                taskData.setPlanDuration(rs.getInt("plan_duration"));
                taskData.setFactStartTime(rs.getDate("fact_start_time"));
                taskData.setFactEndTime(rs.getDate("fact_end_time"));
                taskData.setFactDuration(rs.getInt("fact_duration"));
                taskData.setNumVersion(rs.getInt("num_version"));
                taskData.setIsCompleted(rs.getString("is_completed"));
                listTasks.add(taskData);
            } while (rs.next());
        }

        return listTasks;
    }
    public List<TaskData> getUserAllTasksAsTree(int userID, int nodeID) throws SQLException {
        List<TaskData> listTasks = null;
        if (getDbConnection() == null) throw new SQLException(DBMessage.NO_DB_CONNECTION);
        if (psGetUserAllTasksAsTree == null) {
            psGetUserAllTasksAsTree = getDbConnection().prepareStatement(OracleSQLStatement.Tasks.getUserAllTasksAsTree);
        }

        psGetUserAllTasksAsTree.setInt(1, userID);
        psGetUserAllTasksAsTree.setInt(2, nodeID);
        ResultSet rs = psGetUserAllTasksAsTree.executeQuery();

        if (rs.next()) {
            listTasks = new ArrayList<TaskData>();
            do {
                TaskData taskData = new TaskData();
                taskData.setTaskID(rs.getInt("task_id"));
                taskData.setParentTaskID(rs.getInt("parent_task_id"));
                taskData.setChildTaskID(rs.getInt("child_task_id"));
                taskData.setTaskLevel(rs.getInt("task_level"));
                taskData.setUserID(rs.getInt("user_id"));
                taskData.setTaskName(rs.getString("task_name"));
                taskData.setPlanStartTime(rs.getDate("plan_start_time"));
                taskData.setPlanEndTime(rs.getDate("plan_end_time"));
                taskData.setPlanDuration(rs.getInt("plan_duration"));
                taskData.setFactStartTime(rs.getDate("fact_start_time"));
                taskData.setFactEndTime(rs.getDate("fact_end_time"));
                taskData.setFactDuration(rs.getInt("fact_duration"));
                taskData.setNumVersion(rs.getInt("num_version"));
                taskData.setIsCompleted(rs.getString("is_completed"));
                listTasks.add(taskData);
            } while (rs.next());
        }

        return listTasks;
    }
}
