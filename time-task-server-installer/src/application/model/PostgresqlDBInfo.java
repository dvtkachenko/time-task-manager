package application.model;

/**
 * Created by diman on 23.04.2017.
 */
public final class PostgresqlDBInfo extends DBInfo {

    private final String RDBMS_NAME = "PostgreSQL";

    private final String DB_DRIVER = "org.postgresql.Driver";
//    private final String DB_CONNECTION = "jdbc:postgresql://178.62.247.230:5432/timetaskmanager";
    private final String DB_CONNECTION = "jdbc:postgresql:";

    private final String scriptSuffixFileName = "PostgreSQL.sql";

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