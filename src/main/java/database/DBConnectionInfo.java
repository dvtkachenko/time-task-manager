package database;

/**
 * Created by diman on 23.04.2017.
 */
public abstract class DBConnectionInfo {

    public abstract String getDbDriver();

    public abstract String getDbConnection();

    public abstract String getDbUser();

    public abstract String getDbPassword();
}
