package application.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by diman on 18.05.2017.
 */

/**
 *  This class consists information about database connection for different RDBMS.
 *
 */

public class DBConnectionInfo {

    public enum RDBMS {
        MySQL, PostgreSQL, Oracle;
    }

    private String rdbmsName = null;
    private String scriptSuffixFileName = null;
    private String dbDriver = null;
    private String dbConnectionPrefix = null;
    private String dbDefaultIP = null;
    private String dbDefaultPort = null;
    private String dbFullPathConnection = null;
    private String databaseName = null;
    private String userName = null;
    private String userPassword = null;

    public DBConnectionInfo(DBConnectionInfo.RDBMS selectedRDBMS) throws Exception {

        Properties property = new Properties();

        try (FileInputStream fis = new FileInputStream(InstallationInfo.PATH_TO_RESOURCES + "database.properties")) {

            property.load(fis);
            String keyPrefix = null;

            switch (selectedRDBMS) {
                case MySQL: keyPrefix = "mysql."; break;
                case PostgreSQL: keyPrefix = "postgresql."; break;
                case Oracle: keyPrefix = "oracle."; break;
            }

            rdbmsName = property.getProperty(keyPrefix + "rdbms_name");
            scriptSuffixFileName = property.getProperty(keyPrefix + "script_suffix_filename");
            dbDriver = property.getProperty(keyPrefix + "db_driver");
            dbConnectionPrefix = property.getProperty(keyPrefix + "db_connection_prefix");
            dbDefaultIP = property.getProperty(keyPrefix + "db_default_ip");
            dbDefaultPort = property.getProperty(keyPrefix + "db_default_port");
            databaseName = property.getProperty("database_name");
            userName = property.getProperty("user_name");
            userPassword = property.getProperty("user_password");

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public String getRdbmsName() {
        return rdbmsName;
    }

    public String getScriptSuffixFileName() {
        return scriptSuffixFileName;
    }

    public String getDbDriver() {
        return dbDriver;
    }

    public String getDbConnectionPrefix() {
        return dbConnectionPrefix;
    }

    public String getDbDefaultIP() {
		return dbDefaultIP;
	}

	public String getDbDefaultPort() {
        return dbDefaultPort;
    }

    public String getDbFullPathConnection() {
        return dbFullPathConnection;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setDbFullPathConnection(String dbFullPathConnection) {
        this.dbFullPathConnection = dbFullPathConnection;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
