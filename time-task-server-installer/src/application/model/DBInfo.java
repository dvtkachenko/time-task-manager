package application.model;

/**
 * Created by diman on 23.04.2017.
 */
public abstract class DBInfo {

    private CreationDBScript creationScript = null;

    public abstract String getRDBMSName();

    public abstract String getDbDriver();

    public abstract String getDbConnectionPrefix();

    public abstract String getScriptSuffixFileName();

    public CreationDBScript getDBCreationScript() {

        if (creationScript == null) {
            creationScript = new CreationDBScript(getScriptSuffixFileName());
//            creationScript.initScript(getScriptSuffixFileName());
        }

        return creationScript;
    }

}
