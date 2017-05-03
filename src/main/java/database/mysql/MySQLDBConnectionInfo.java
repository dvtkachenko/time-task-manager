package database.mysql;

import database.DBConnectionInfo;

/**
 * Created by diman on 23.04.2017.
 */
public final class MySQLDBConnectionInfo extends DBConnectionInfo{
    private final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_CONNECTION = "jdbc:mysql://178.62.247.230:3306/timetaskmanager";

    private final String DB_USER = "timetaskmanager";
    private final String DB_PASSWORD = "12345";

    public String getDbDriver() {
        return DB_DRIVER;
    }

    public String getDbConnection() {
        return DB_CONNECTION;
    }

    public String getDbUser() {
        return DB_USER;
    }

    public String getDbPassword() {
        return DB_PASSWORD;
    }
}