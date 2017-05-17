package application.model;

import java.io.FileInputStream;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by diman on 17.05.2017.
 */
public class DBCreationScript {

    private final String PATH_TO_SCRIPT = "";

    private List<String> user = null;
    private List<String> database = null;
    private List<String> tables = null;

    private boolean isReady = false;

    public List<String> getUser() {
        return user;
    }

    public List<String> getDatabase() {
        return database;
    }

    public List<String> getTables() {
        return tables;
    }

    public boolean isReady() {
        return isReady;
    }

    @Override
    public String toString() {
        String script = null;

        for(String s : database) {
            script += s;
        }

        for(String s : tables) {
            script += s;
        }

        return script;
    }

    public void initScript(String scriptSuffixFileName) {

        try (FileInputStream fileUserScript = new FileInputStream(PATH_TO_SCRIPT + "userScript" + scriptSuffixFileName)) {

            user = new ArrayList<String>();
            readFileScript(user, fileUserScript);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try (FileInputStream fileDatabaseScript = new FileInputStream(PATH_TO_SCRIPT + "databaseScript" + scriptSuffixFileName)) {

            database = new ArrayList<String>();
            readFileScript(database, fileDatabaseScript);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try (FileInputStream fileTablesScript = new FileInputStream(PATH_TO_SCRIPT + "tablesScript" + scriptSuffixFileName)) {

            tables = new ArrayList<String>();
            readFileScript(tables, fileTablesScript);

        } catch (Exception e) {
            e.printStackTrace();
        }

        isReady = true;
    }

    private void readFileScript(List<String> script, FileInputStream fileScript) {
        Scanner s = new Scanner(fileScript);
        s.useDelimiter("(;(\r)?\n)|(--\n)");
        while (s.hasNext())
        {
            String line = s.next();
            if (line.startsWith("/*!") && line.endsWith("*/"))
            {
                int i = line.indexOf(' ');
                line = line.substring(i + 1, line.length() - " */".length());
            }

            if (line.trim().length() > 0)
            {
                script.add(line);
            }
        }
    }
}
