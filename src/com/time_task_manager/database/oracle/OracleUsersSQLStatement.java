package com.time_task_manager.database.oracle;

/**
 * Created by diman on 21.04.2017.
 */
final public class OracleUsersSQLStatement {
    public static String addUser = "insert into users values (seq_users.nextval, ?, ?)";
    public static String deleteUser = "delete from users u where u.user_name = ?";
    public static String changePassword = "update users u set u.user_password = ? where u.user_name = ?";
    public static String getUserData = "select u.user_id, u.user_name, u.user_password from users u where u.user_name = ?";
    public static String getAllUsersData = "select u.user_id, u.user_name, u.user_password from users u";
    public static String getMaxUserID = "select max(user_id) from users";
}
