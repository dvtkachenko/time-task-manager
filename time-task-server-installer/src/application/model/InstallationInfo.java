package application.model;

import java.net.URL;

/**
 * Created by diman on 17.05.2017.
 */
public class InstallationInfo {

    private int stepInstallation = 0;

    private String pathToInstallation;

    private DBInfo connectionInfo = null;

    private boolean createUserAuto = false;

    private String adminRDBMSUsername = null;

    private String adminRDBMSPAssword = null;

    private String ttmUserName = null;

    private String ttmUserPassword = null;

    private URL serverIP = null;

    private String serverPort = null;

    private boolean launchServer = true;

}
