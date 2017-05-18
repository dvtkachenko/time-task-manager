package application.model;

import java.util.List;

/**
 * Created by diman on 18.05.2017.
 */
public interface DBCreationScriptInterface {
    List<String> getUser();

    List<String> getDatabase();

    List<String> getTables();

}