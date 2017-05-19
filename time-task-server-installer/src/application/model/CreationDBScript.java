package application.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by diman on 17.05.2017.
 */
public class CreationDBScript {

//    private final String PATH_TO_SCRIPT = "d:\\MyDevelopment\\JavaCourse\\TTMServerInstaller\\out\\production\\TTMServerInstaller\\application\\resources\\";
    private final String PATH_TO_SCRIPT = "out\\production\\TTMServerInstaller\\application\\resources\\";

    private String userScript = null;
    private String databaseScript = null;
    private String tablesScript = null;

    private List<String> userJDBCStatement = null;
    private List<String> databaseJDBCStatement = null;
    private List<String> tablesJDBCStatement = null;

    private boolean isReady = false;

    public String getUser() {
        return userScript;
    }

    public void setUserScript(String userScript) {
        this.userScript = userScript;
    }

    public String getDatabaseScript() {
        return databaseScript;
    }

    public void setDatabaseScript(String databaseScript) {
        this.databaseScript = databaseScript;
    }

    public String getTablesScript() {
        return tablesScript;
    }

    public void setTablesScript(String tablesScript) {
        this.tablesScript = tablesScript;
    }

    public List<String> getUserJDBCStatement() {
        return userJDBCStatement;
    }

    public List<String> getDatabaseJDBCStatement() {
        return databaseJDBCStatement;
    }

    public List<String> getTablesJDBCStatement() {
        return tablesJDBCStatement;
    }

    public boolean isReady() {
        return isReady;
    }

//    @Override
//    public String toString() {
//        String script = null;
//
//        for(String s : database) {
//            script += s;
//        }
//
//        for(String s : tables) {
//            script += s;
//        }
//
//        return script;
//    }

//    CreationDBScript(String scriptSuffixFileName) {
//        initScript(scriptSuffixFileName);
//    }

    private void makeAllJDBCStatements() {

        userJDBCStatement = new ArrayList<String>();
        parseStringToJDBCStatements(userScript, userJDBCStatement);

        databaseJDBCStatement = new ArrayList<String>();
        parseStringToJDBCStatements(databaseScript, databaseJDBCStatement);

        tablesJDBCStatement = new ArrayList<String>();
        parseStringToJDBCStatements(tablesScript, tablesJDBCStatement);

        isReady = true;
    }


    private void parseStringToJDBCStatements(String sourceString, List<String> userScript) {

        StringReader in = new StringReader(sourceString);

        Scanner s = new Scanner(in);
//        s.useDelimiter("(;(\r)?\n)|((\r)?\n)?(--)?.*(--(\r)?\n)");
        s.useDelimiter("(;(\r)?\n)|(--\n)");
        while (s.hasNext())
        {
            String line = s.next();
            if (line.startsWith("/*!") && line.endsWith("*/"))
            {
                int i = line.indexOf(' ');
                line = line.substring(i + 1, line.length() - " */".length());
            }

            if (!line.startsWith("--") && !line.startsWith("\n--") && line.trim().length() > 0)
            {
                userScript.add(line);
            }
        }
        s.close();

    }

    private void readScriptFiles(String scriptSuffixFileName) {

        try (FileInputStream fileUserScript = new FileInputStream(PATH_TO_SCRIPT + "userScript" + scriptSuffixFileName)) {

            userScript = readFileToString(fileUserScript);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try (FileInputStream fileDatabaseScript = new FileInputStream(PATH_TO_SCRIPT + "databaseScript" + scriptSuffixFileName)) {

            databaseScript = readFileToString(fileDatabaseScript);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try (FileInputStream fileTablesScript = new FileInputStream(PATH_TO_SCRIPT + "tablesScript" + scriptSuffixFileName)) {

            tablesScript = readFileToString(fileTablesScript);

        } catch (Exception e) {
            e.printStackTrace();
        }

        isReady = true;
    }

    private String readFileToString(FileInputStream fileScript) throws IOException {
        byte[] str = new byte[fileScript.available()];
        fileScript.read(str);
        return new String(str);
    }

}
