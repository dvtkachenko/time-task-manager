package application.model;


/**
 * Created by diman on 23.04.2017.
 */
public final class MySQLDBInfo extends DBInfo {

    private final String RDBMS_NAME = "MySQL";

    private final String DB_DRIVER = "com.mysql.jdbc.Driver";
//    private final String DB_CONNECTION = "jdbc:mysql://178.62.247.230:3306/timetaskmanager";
    private final String DB_CONNECTION = "jdbc:mysql:";

    private final String scriptSuffixFileName = "MySQL.sql";

    public String getRDBMSName() {
        return RDBMS_NAME;
    }

    public String getDbDriver() {
        return DB_DRIVER;
    }

    public String getDbConnectionPrefix() {
        return DB_CONNECTION;
    }

    public String getScriptSuffixFileName() {
        return scriptSuffixFileName;
    }

}