package com.time_task_manager.model;

import java.sql.Connection;

/**
 * Created by diman on 19.04.2017.
 */

public class UserData {

    private int userID = 0;
    private String userName;
    private String userPassword;

    public UserData() {
        super();
    }

    public UserData(String userName) {
        this.userName = userName;
    }

    public UserData(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public UserData(int userID, String userName, String userPassword) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) { this.userID = userID; }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
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
        return  "UserData{" +
                "user_id=" + userID +
                ", user_name='" + userName + '\'' +
                ", user_password='" + userPassword + '\'' +
                '}' + '\n';
    }
}
