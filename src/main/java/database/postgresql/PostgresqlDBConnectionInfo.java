package database.postgresql;

import database.DBConnectionInfo;

/**
 * Created by diman on 23.04.2017.
 */
public final class PostgresqlDBConnectionInfo extends DBConnectionInfo{
    private final String DB_DRIVER = "org.postgresql.Driver";
    private final String DB_CONNECTION = "jdbc:postgresql://178.62.247.230:5432/timetaskmanager";

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