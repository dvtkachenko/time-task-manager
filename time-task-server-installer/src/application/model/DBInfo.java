package application.model;

/**
 * Created by diman on 23.04.2017.
 */
public abstract class DBInfo {

    private DBCreationScript creationScript = null;

    public abstract String getRDBMSName();

    public abstract String getDbDriver();

    public abstract String getDbConnectionPrefix();

    public DBCreationScript getDBCreationScript(String scriptSuffixFileName) {

        if (creationScript == null) {
            creationScript = new DBCreationScript();
            creationScript.initScript(scriptSuffixFileName);
        }

        return creationScript;
    }

}
