package com.time_task_manager.database;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * Created by diman on 19.04.2017.
 */

/**
class UserDBData {
    private int userID;
    private String userName;
    private String userPassword;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) { this.userID = userID; }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String user_name) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "UsersDBData{" +
                "user_id=" + userID +
                ", user_name='" + userName + '\'' +
                ", user_password='" + userPassword + '\'' +
                '}';
    }
}

interface UserDBAction {
    boolean addUser(String userName, String userPassword);
    boolean deleteUser(String userName);
    boolean changePassword(String userName, String userPassword);
    //    boolean checkUser(String userName);
    UserDBData getUserData(String userName);
    List<UserDBData> getAllUsersData();
}

class TaskData {

    private int taskID;
    private int parentTaskID;
    private int childTaskID;
    private int levelTaskID;
    private int userID;
    private String taskName;
    private Date plantStartTime;
    private Date planEndTime;
    private int planDuration;
    private Date factStartTime;
    private Date factEndTime;
    private int factDuration;
    private int version;
    private boolean isCompleted;

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public int getParentTaskID() {
        return parentTaskID;
    }

    public void setParentTaskID(int parentTaskID) {
        this.parentTaskID = parentTaskID;
    }

    public int getChildTaskID() {
        return childTaskID;
    }

    public void setChildTaskID(int childTaskID) {
        this.childTaskID = childTaskID;
    }

    public int getLevelTaskID() {
        return levelTaskID;
    }

    public void setLevelTaskID(int levelTaskID) {
        this.levelTaskID = levelTaskID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getPlantStartTime() {
        return plantStartTime;
    }

    public void setPlantStartTime(Date plantStartTime) {
        this.plantStartTime = plantStartTime;
    }

    public Date getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(Date planEndTime) {
        this.planEndTime = planEndTime;
    }

    public int getPlanDuration() {
        return planDuration;
    }

    public void setPlanDuration(int planDuration) {
        this.planDuration = planDuration;
    }

    public Date getFactStartTime() {
        return factStartTime;
    }

    public void setFactStartTime(Date factStartTime) {
        this.factStartTime = factStartTime;
    }

    public Date getFactEndTime() {
        return factEndTime;
    }

    public void setFactEndTime(Date factEndTime) {
        this.factEndTime = factEndTime;
    }

    public int getFactDuration() {
        return factDuration;
    }

    public void setFactDuration(int factDuration) {
        this.factDuration = factDuration;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}

interface TaskDBAction {
}

*/
public interface DBAction {
    boolean openDBConnection(DBConnectionInfo dbci);
    void closeDBConnection();
}
