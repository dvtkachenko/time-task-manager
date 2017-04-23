package com.time_task_manager.database.oracle;

/**
 * Created by diman on 23.04.2017.
 */
public final class OracleDBConnectionInfo {
    public final static String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public final static String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";

    public final static String DB_USER = "TIMETASKMANAGER";
    //    public static final String DB_USER = "SYSTEM";
    public final static String DB_PASSWORD = "12345";
//    public static final String DB_PASSWORD = "admin1977";
//    public static final String selectTableSQL = "SELECT * FROM USERS";
}