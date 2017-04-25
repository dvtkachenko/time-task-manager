package database.oracle;

import database.DBConnectionInfo;

/**
 * Created by diman on 23.04.2017.
 */
public final class OracleDBConnectionInfo extends DBConnectionInfo{
    private final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";

    private final String DB_USER = "TIMETASKMANAGER";
    //    private final String DB_USER = "SYSTEM";
    private final String DB_PASSWORD = "12345";
//    private final String DB_PASSWORD = "admin1977";
//    private final String selectTableSQL = "SELECT * FROM USERS";

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