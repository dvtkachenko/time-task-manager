package database;

/**
 * Created by diman on 19.04.2017.
 */


public interface DBAction {
    boolean openDBConnection(DBConnectionInfo dbci) throws Exception;
    void closeDBConnection() throws Exception;
}
