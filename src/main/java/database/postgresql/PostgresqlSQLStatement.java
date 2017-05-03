package database.postgresql;

/**
 * Created by diman on 24.04.2017.
 */

public class PostgresqlSQLStatement {
    static class Users {
        public static String addUser = "insert into users (user_id, user_name, user_password) values (nextval('seq_users'), ?, ?)";
        public static String deleteUser = "delete from users where user_name = ?";
        public static String changePassword = "update users set user_password = ? where user_name = ?";
        public static String getUserData = "select user_id, user_name, user_password from users where user_name = ?";
        public static String getAllUsersData = "select user_id, user_name, user_password from users order by user_id";
        public static String getMaxUserID = "select max(user_id) from users";
    }
    static class Tasks {

        public static String addUserTask = "insert into TASKS (TASK_ID, PARENT_TASK_ID, CHILD_TASK_ID, TASK_LEVEL, " +
                "USER_ID, TASK_NAME, PLAN_START_TIME, PLAN_END_TIME, PLAN_DURATION, FACT_START_TIME, FACT_END_TIME, " +
                "FACT_DURATION, NUM_VERSION, IS_COMPLETED) VALUES (seq_tasks.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        public static String deleteUserTask = "delete from TASKS where task_id = ? and user_id = ?";

        public static String deleteUserAllTasks = "delete from TASKS where user_id = ?";

        public static String updateUserTask = "update TASKS SET PARENT_TASK_ID = ?, CHILD_TASK_ID = ?, TASK_LEVEL = ?," +
                " USER_ID = ?, TASK_NAME = ?, PLAN_START_TIME = ?, PLAN_END_TIME = ?, PLAN_DURATION = ?, " +
                "FACT_START_TIME = ?, FACT_END_TIME = ?, FACT_DURATION = ?, NUM_VERSION = ?, IS_COMPLETED = ? " +
                "where TASK_ID = ? and USER_ID = ?";

        public static String getUserTask = "select * from TASKS where TASK_ID = ? and USER_ID = ?";
        public static String getUserAllTasks = "select * from TASKS where USER_ID = ?";
        public static String getUserAllTasksAsTree = "SELECT * FROM TASKS WHERE USER_ID = ? START WITH TASK_ID = ? " +
                "CONNECT BY NOCYCLE PARENT_TASK_ID = PRIOR TASK_ID";
    }
}
