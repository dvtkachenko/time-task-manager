package com.time_task_manager.database;

import com.time_task_manager.model.UserData;

import java.util.List;

/**
 * Created by diman on 19.04.2017.
 */
public interface UserDBAction {
    boolean addUser(String userName, String userPassword);
    boolean deleteUser(String userName);
    boolean changePassword(String userName, String userPassword);
    //    boolean checkUser(String userName);
    UserData getUserData(String userName);
    List<UserData> getAllUsersData();
}