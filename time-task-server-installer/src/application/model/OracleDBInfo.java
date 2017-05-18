package application.model;

/**
 * Created by diman on 23.04.2017.
 */
public final class OracleDBInfo extends DBInfo {

    private final String RDBMS_NAME = "Oracle Express";

    private final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
//    private final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";
    private final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";

    private final String scriptSuffixFileName = "Oracle.sql";

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